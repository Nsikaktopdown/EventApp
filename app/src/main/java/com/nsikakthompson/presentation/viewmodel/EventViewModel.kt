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


    val events by lazy {
        eventRepository.observePagedEvents(true, ioCoroutineScope)
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