package com.nsikakthompson.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.data.AppPageDataSourceFactory
import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.domain.usecase.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber


/**
 * Ui state for the event screen
 */
sealed interface EventUIState {
    val isLoading: Boolean
    val errorMessage: String

    data class HasEvent(
        override val isLoading: Boolean,
        override val errorMessage: String,
        val eventFeed: List<EventEntity>
    ) : EventUIState

    data class NoEvent(
        override val isLoading: Boolean,
        override val errorMessage: String
    ) : EventUIState
}

/**
 * internal representation of the event list route
 */

private data class EventViewModelState(
    val eventFeed: PagedList<EventEntity>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
) {
    /**
     * Converts this [EventViewModelState] into a more strongly typed [EventUIState] for driving
     * the ui.
     */
    fun toUiState(): EventUIState =
        if (eventFeed == null) {
            EventUIState.NoEvent(
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        } else {
            EventUIState.HasEvent(
                isLoading = isLoading,
                errorMessage = errorMessage,
                eventFeed = eventFeed
            )
        }
}


class EventViewModel(
    private var getEventListUseCase: GetEventListUseCase,
    private var addToWishListUseCase: AddToWishListUseCase,
    private var removeFromWishListUseCase: RemoveFromWishListUseCase,
    private var getWishListCountUseCase: GetWishListCountUseCase,
    private var getEventByIdUseCase: GetEventByIdUseCase,
    private var ioCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    private var appPageDataSourceFactory: AppPageDataSourceFactory
) : ViewModel() {

    private val viewModelState = MutableStateFlow(EventViewModelState(isLoading = false))

    private val _event: MutableLiveData<EventEntity> = MutableLiveData()
    var event : LiveData<EventEntity> = _event

    private val _wishListCount: MutableLiveData<Int> = MutableLiveData()
    var wishCount: LiveData<Int> = _wishListCount

    val networkState: LiveData<NetworkState>? =
        switchMap(appPageDataSourceFactory.getSource()) { it.getNetworkState() }


    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    fun getEvents(){
        viewModelState.update { it.copy(isLoading = true) }
        viewModelState.update {
            it.copy(
                eventFeed = getEventListUseCase.call(
                    GetEventListUseCase.Params(
                        ioCoroutineScope,
                        false
                    )
                ).value
            )
        }
    }

    val events by lazy {
        getEventListUseCase.call(
            GetEventListUseCase.Params(
                ioCoroutineScope,
                false
            )
        )
    }




    val wishList by lazy {
        getEventListUseCase.call(
            GetEventListUseCase.Params(
                ioCoroutineScope,
                true
            )
        )
    }

    fun addWishList(event: EventEntity) {
        viewModelScope.launch {
            try {
                addToWishListUseCase.call(event)
            } catch (error: Throwable) {
                Timber.e(error.message)
            }

        }
        getEventById(event.id)
    }

    fun removeWishList(event: EventEntity) {
        viewModelScope.launch {
            try {
                removeFromWishListUseCase.call(event)
            } catch (error: Throwable) {
                Timber.e(error.message)
            }

        }
    }

    fun getEventById(event_id: String) {
        viewModelScope.launch {
            _event.postValue(getEventByIdUseCase.call(event_id))
        }
    }


    fun getWishCount() {
        viewModelScope.launch {
            _wishListCount.postValue(getWishListCountUseCase.call())
        }

    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}


enum class EventState {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState constructor(var state: EventState, var message: String? = null)