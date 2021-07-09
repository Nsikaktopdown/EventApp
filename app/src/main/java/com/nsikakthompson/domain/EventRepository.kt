package com.nsikakthompson.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.data.Result
import kotlinx.coroutines.CoroutineScope

interface EventRepository {
     fun  observePagedEvents(connectivityAvailable: Boolean,coroutineScope: CoroutineScope):
            LiveData<PagedList<EventEntity>>
     fun observeEvent(): LiveData<Result<List<EventEntity>>>
}