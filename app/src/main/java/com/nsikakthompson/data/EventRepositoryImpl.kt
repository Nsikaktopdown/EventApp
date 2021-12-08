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
    private var dataSourceFactory: AppPageDataSourceFactory
) : EventRepository {

    override fun observePagedEvents(
        connectivityAvailable: Boolean,
        isWish: Boolean
    ) =
        if (connectivityAvailable)

            observeRemotePagedEvents()
        else observeLocalPagedEvents(isWish)

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


    private fun observeLocalPagedEvents(isWish: Boolean): LiveData<PagedList<EventEntity>> {
        val dataSourceFactory = dao.getPagedEvents(isWish)
        return LivePagedListBuilder(
            dataSourceFactory,
            AppPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    private fun observeRemotePagedEvents()
            : LiveData<PagedList<EventEntity>> {
        return LivePagedListBuilder(
            dataSourceFactory,
            AppPageDataSourceFactory.pagedListConfig()
        ).build()
    }


}