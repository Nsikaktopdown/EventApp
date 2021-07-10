package com.nsikakthompson.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.data.Result
import kotlinx.coroutines.CoroutineScope

interface EventRepository {
     fun  observePagedEvents(connectivityAvailable: Boolean,coroutineScope: CoroutineScope):
            LiveData<PagedList<EventEntity>>
     suspend fun addToWishList(eventEntity: EventEntity)
     suspend fun removeWishList(eventEntity: EventEntity)
     suspend fun getEventById(event_id: String): EventEntity
}