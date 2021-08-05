package com.nsikakthompson.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope

class GetEventListUseCase(
    private val isNetworkAvailable: Boolean,
    private val eventRepository: EventRepository
) {
    fun call(input: Params): LiveData<PagedList<EventEntity>> {

        return eventRepository.observePagedEvents(
            isNetworkAvailable,
            input.coroutineScope,
            input.isWish
        )

    }

    data class Params(val coroutineScope: CoroutineScope, val isWish: Boolean)
}