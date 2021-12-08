package com.nsikakthompson.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.data.EventPagedDataSource
import kotlinx.coroutines.flow.Flow

class GetEventListUseCase(
    private var eventPagedDataSource: EventPagedDataSource
) {
    fun call(): Flow<PagingData<EventEntity>> {
        return Pager(PagingConfig(pageSize = 20)) {
            eventPagedDataSource
        }.flow
    }
}