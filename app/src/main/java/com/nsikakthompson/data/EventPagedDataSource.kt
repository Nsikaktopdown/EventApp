package com.nsikakthompson.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.presentation.viewmodel.EventState
import com.nsikakthompson.presentation.viewmodel.NetworkState
import com.nsikakthompson.utils.DispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber


/**
 * Data source for lego sets pagination via paging library
 */

class EventPagedDataSource(
    private val dataSource: AppRemoteDataSource,
    private val dao: EventDao,
    private val scope: CoroutineScope,
) : PageKeyedDataSource<Int, EventEntity>() {

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private val PAGE_SIZE = 10


    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
        // networkState.postValue(EventState.Error(message))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EventEntity>) {
        val page = params.key
        fetchData(page, PAGE_SIZE) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EventEntity>) {
        val page = params.key
        fetchData(page, PAGE_SIZE) {
            callback.onResult(it, page - 1)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, EventEntity>
    ) {
        fetchData(1, PAGE_SIZE) {
            callback.onResult(it, null, 2)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<EventEntity>) -> Unit) {
        scope.launch(getJobErrorHandler() + supervisorJob) {
            dataSource.fetchEvents(page, pageSize)
                .onStart { networkState.postValue(NetworkState(EventState.RUNNING)) }
                .catch { exception ->
                    networkState.postValue(
                        NetworkState(
                            EventState.FAILED,
                            message = exception.message ?: "Failed to fetch events"
                        )
                    )
                }.onCompletion {
                    networkState.postValue(NetworkState(EventState.SUCCESS))
                }
                .collect { response ->
                    val results = response._embedded.events.map {
                        EventEntity(
                            it.id,
                            it.name,
                            it.images[0].url,
                            it.sales.public.startDateTime ?: "",
                            it.sales.public.endDateTime ?: "",
                            it.promoter?.name ?: "",
                            it.promoter?.description,
                            if (it.priceRanges != null) it.priceRanges[0].min else 0.0,
                            if (it.priceRanges != null) it.priceRanges[0].currency else "",
                            if (it.priceRanges != null) it.priceRanges[0].type else "Unknown",
                            it.embedded.venues[0].name ?: "",
                            it.embedded.venues[0].state.name,
                            it.embedded.venues[0].boxOfficeInfo?.openHoursDetail ?: "",
                            it.embedded.venues[0].boxOfficeInfo?.acceptedPaymentDetail ?: "",
                            it.embedded.venues[0].boxOfficeInfo?.willCallDetail ?: "",
                            false
                        )

                    }
                    //dao.insertAll(results)
                    Timber.e("${results.size} result size")
                    callback(results)

                }


        }
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
    }

    // PUBLIC API ---

    fun getNetworkState(): LiveData<NetworkState> =
        networkState


}
