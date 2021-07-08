package com.nsikakthompson.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class EventViewModel(
    private var eventRepository: EventRepository,
    private val ioCoroutineScope: CoroutineScope
) : BaseViewModel<EventState>() {
    var connectivityAvailable: Boolean = true

    fun getEvents() {
        //uiState.value = EventState.Loading
        try {
            val events =
                eventRepository.observePagedEvents(true, ioScope)
        } catch (error: Throwable) {
            uiState.value = EventState.Error(error.message ?: "failed to fetch events")
        }

    }


//    fun getEvents(): LiveData<PagedList<EventEntity>> =
//        eventRepository.observePagedEvents(connectivityAvailable, ioCoroutineScope)
//
//
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