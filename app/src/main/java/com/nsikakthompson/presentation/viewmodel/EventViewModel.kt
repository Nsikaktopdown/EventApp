package com.nsikakthompson.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class EventViewModel(
    private var eventRepository: EventRepository,
    private val ioCoroutineScope: CoroutineScope,
    private val isNetworkAvailable: Boolean
) : BaseViewModel<EventState>() {


    private val _event: MutableLiveData<EventEntity> = MutableLiveData()
    var event: LiveData<EventEntity> = _event

    private val _wishListCount: MutableLiveData<Int> = MutableLiveData()
    var wishCount: LiveData<Int> = _wishListCount

    val events by lazy {
        eventRepository.observePagedEvents(isNetworkAvailable, ioCoroutineScope)
    }

    fun addWishList(event: EventEntity){
        viewModelScope.launch {
            try{
                eventRepository.addToWishList(event)
            }catch(error: Throwable){
                Timber.e(error.message)
            }

        }
    }

    fun removeWishList(event: EventEntity){
        viewModelScope.launch {
            try{
                eventRepository.removeWishList(event)
            }catch(error: Throwable){
                Timber.e(error.message)
            }

        }
    }

    fun getEventById(event_id: String){
        viewModelScope.launch {
            _event.postValue(eventRepository.getEventById(event_id))
        }
    }


    fun getWishCount(){
        viewModelScope.launch {
            _wishListCount.postValue(eventRepository.getCount())
        }
    }
    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

}

sealed class EventState {
    object Loading : EventState()
    object Success : EventState()
    data class data(val events: LiveData<PagedList<EventEntity>>) : EventState()
    data class Error(val message: String) : EventState()
}