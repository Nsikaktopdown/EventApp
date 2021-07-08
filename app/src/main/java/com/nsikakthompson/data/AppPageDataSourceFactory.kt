package com.nsikakthompson.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.nsikakthompson.api.Event
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import kotlinx.coroutines.CoroutineScope

class AppPageDataSourceFactory constructor (
    private val dataSource: AppRemoteDataSource,
    private val dao: EventDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, EventEntity>() {

    private val liveData = MutableLiveData<EventPageDataSource>()

    override fun create(): DataSource<Int, EventEntity> {
        val source = EventPageDataSource(dataSource, dao, scope)
        this.liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 10

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

}