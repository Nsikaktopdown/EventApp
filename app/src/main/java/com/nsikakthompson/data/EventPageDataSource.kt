package com.nsikakthompson.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.presentation.viewmodel.EventState
import kotlinx.coroutines.*
import timber.log.Timber


/**
 * Data source for lego sets pagination via paging library
 */

class EventPageDataSource(
    private val dataSource: AppRemoteDataSource,
    private val dao: EventDao,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, EventEntity>() {

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<EventState>()


    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
        networkState.postValue(EventState.Error(message))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EventEntity>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EventEntity>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, EventEntity>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<EventEntity>) -> Unit) {
        networkState.value = EventState.Loading
        scope.launch(getJobErrorHandler()+ supervisorJob) {
            delay(100)
            val response = dataSource.fetchEvents(page, pageSize)
                val results = response.data!!.embedded.events.map {
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

                    )
                }
                networkState.value = EventState.Success
                dao.insertAll(results)
                callback(results)

        }
    }
    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
    }

    // PUBLIC API ---

    fun getNetworkState(): LiveData<EventState> =
        networkState


}
