package com.nsikakthompson.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.usecase.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
        val eventFeed: Flow<PagingData<EventEntity>>
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
    val eventFeed: Flow<PagingData<EventEntity>>? = null,
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
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(EventViewModelState(isLoading = false))

    private val _wishListState = MutableStateFlow(false)

    private val _event: MutableLiveData<EventEntity> = MutableLiveData()
    var event: LiveData<EventEntity> = _event

    private val _wishListCountState = MutableStateFlow(0)

    var wishCount = _wishListCountState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        _wishListCountState.value
    )

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    val wishListUIState = _wishListState
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _wishListState.value
        )

    init {
        viewModelScope.launch(dispatcher) {
            val events = getEventListUseCase.call()
            viewModelState.update {
                it.copy(
                    eventFeed = events
                )
            }
        }
    }

    fun refreshEvents() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch(dispatcher) {
            val events = getEventListUseCase.call()
            viewModelState.update {
                it.copy(eventFeed = events)
            }
        }

    }

    val events by lazy {
        getEventListUseCase.call()
    }


    fun addWishList(event: EventEntity) {
        viewModelScope.launch(dispatcher) {
            try {
                addToWishListUseCase.call(event)
                _wishListState.value = true
            } catch (error: Throwable) {
                _wishListState.value = false
                Timber.e(error.message)
            }
        }
    }

    fun removeWishList(event: EventEntity) {
        viewModelScope.launch {
            try {
                removeFromWishListUseCase.call(event)
                _wishListState.value = false
            } catch (error: Throwable) {
                _wishListState.value = false
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
            _wishListCountState.value = getWishListCountUseCase.call()
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


