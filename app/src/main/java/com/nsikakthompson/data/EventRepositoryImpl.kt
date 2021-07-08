package com.nsikakthompson.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.cache.resultLiveData
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope

class EventRepositoryImpl(private var dao: EventDao,
                          private var appRemoteDataSource: AppRemoteDataSource,
): EventRepository {

    override fun observePagedEvents(connectivityAvailable: Boolean,coroutineScope: CoroutineScope) =
       observeRemotePagedEvents(coroutineScope)
     //   else observeLocalPagedEvents()


    private fun observeLocalPagedEvents(): LiveData<PagedList<EventEntity>> {
        val dataSourceFactory = dao.getPagedEvents()
        return LivePagedListBuilder(dataSourceFactory,
         AppPageDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeRemotePagedEvents( ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<EventEntity>> {
        val dataSourceFactory = AppPageDataSourceFactory(appRemoteDataSource,
            dao, ioCoroutineScope)
        return LivePagedListBuilder(dataSourceFactory,
           AppPageDataSourceFactory.pagedListConfig()).build()
    }


    fun observeEvent() = resultLiveData(
        databaseQuery = { dao.getEvents() },
        networkCall = { appRemoteDataSource.fetchEvents(1, 10) },
        saveCallResult = { dao.insertAll(it.embedded.events.map {
            EventEntity(
                it.id,
                it.name,
                it.images[0].url,
                it.sales.public.startDateTime,
                it.sales.public.endDateTime,
                it.promoter.name,
                it.promoter.description,
                it.priceRanges[0].min,
                it.priceRanges[0].currency,
                it.embedded[0].venues.name,
                it.embedded[0].venues.state.name,
                false
            ) })})



}