package com.nsikakthompson.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope

class EventRepositoryImpl(
    private var dao: EventDao,
    private var appRemoteDataSource: AppRemoteDataSource
) : EventRepository {
    override suspend fun getEvents(page: Int, size: Int) = appRemoteDataSource.fetchEvents(page = page, size)

    override suspend fun addToWishList(eventEntity: EventEntity) {
        return dao.insert(eventEntity)
    }

    override suspend fun removeWishList(eventEntity: EventEntity) {
        return dao.updateIsWish(false, eventEntity.id)
    }

    override suspend fun getEventById(event_id: String): EventEntity {
        return dao.getEventById(event_id)
    }

    override suspend fun getCount(): Int {
        return dao.getCount(true)
    }



}