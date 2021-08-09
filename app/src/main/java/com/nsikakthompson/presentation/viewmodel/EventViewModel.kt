package com.nsikakthompson.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.data.AppPageDataSourceFactory
import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.domain.usecase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class EventViewModel(
    private var getEventListUseCase: GetEventListUseCase,
    private var addToWishListUseCase: AddToWishListUseCase,
    private var removeFromWishListUseCase: RemoveFromWishListUseCase,
    private var getWishListCountUseCase: GetWishListCountUseCase,
    private var getEventByIdUseCase: GetEventByIdUseCase,
    private var ioCoroutineScope: CoroutineScope,
    private var appPageDataSourceFactory: AppPageDataSourceFactory
) : BaseViewModel<EventState>() {


    private val _event: MutableLiveData<EventEntity> = MutableLiveData()
    var event: LiveData<EventEntity> = _event

    private val _wishListCount: MutableLiveData<Int> = MutableLiveData()
    var wishCount: LiveData<Int> = _wishListCount

    val networkState: LiveData<NetworkState>? = switchMap(appPageDataSourceFactory.getSource()) { it.getNetworkState() }


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

//sealed class EventState {
//    object Loading : EventState()
//    object Success : EventState()
//    data class data(val events: LiveData<PagedList<EventEntity>>) : EventState()
//    data class Error(val message: String) : EventState()
//}

enum class EventState {
    RUNNING,
    SUCCESS,
    FAILED
}
data class NetworkState constructor(var state: EventState, var message: String? = null )