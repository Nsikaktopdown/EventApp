package com.nsikakthompson.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.cache.resultLiveData
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope

class EventRepositoryImpl(
    private var dao: EventDao,
    private var appRemoteDataSource: AppRemoteDataSource,
) : EventRepository {

    override fun observePagedEvents(
        connectivityAvailable: Boolean,
        coroutineScope: CoroutineScope
    ) =
        if (connectivityAvailable)

            observeRemotePagedEvents(coroutineScope)
        else observeLocalPagedEvents()

    override suspend fun updateIsWish(isWish: Boolean, event_id: String) {
        return dao.updateIsWish(isWish, event_id)
    }


    private fun observeLocalPagedEvents(): LiveData<PagedList<EventEntity>> {
        val dataSourceFactory = dao.getPagedEvents()
        return LivePagedListBuilder(
            dataSourceFactory,
            AppPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    private fun observeRemotePagedEvents(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<EventEntity>> {
        val dataSourceFactory = AppPageDataSourceFactory(
            appRemoteDataSource,
            dao, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            AppPageDataSourceFactory.pagedListConfig()
        ).build()
    }





}