package com.nsikakthompson.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nsikakthompson.cache.EventEntity
import retrofit2.HttpException
import java.io.IOException


/**
 * Data source for lego sets pagination via paging library
 */

class EventPagedDataSource(
    private val dataSource: AppRemoteDataSource,
) : PagingSource<Int, EventEntity>() {

    private val PAGE_SIZE = 10
    override fun getRefreshKey(state: PagingState<Int, EventEntity>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventEntity> {
        return try {
            val nextPage = params.key ?: 1
            val results = dataSource.fetchEvents(nextPage, PAGE_SIZE)
            LoadResult.Page(
                data = results._embedded.events.map {
                    EventEntity(
                        it.id,
                        it.name,
                        it.images[0].url,
                        it.sales.public.startDateTime ?: "",
                        it.sales.public.endDateTime ?: "",
                        it.promoter?.name ?: "",
                        it.promoter?.description,
                        it.priceRanges?.first()?.min ?: 0.0,
                        it.priceRanges?.first()?.currency ?: "",
                        it.priceRanges?.first()?.type ?: "Unknown",
                        it.embedded.venues[0].name ?: "",
                        it.embedded.venues[0].state.name,
                        it.embedded.venues[0].boxOfficeInfo?.openHoursDetail ?: "",
                        it.embedded.venues[0].boxOfficeInfo?.acceptedPaymentDetail ?: "",
                        it.embedded.venues[0].boxOfficeInfo?.willCallDetail ?: "",
                        false
                    )

                },
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


//    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
//        postError(e.message ?: e.toString())
//    }
//
//    private fun postError(message: String) {
//        Timber.e("An error happened: $message")
//        // TODO network error handling
//        // networkState.postValue(EventState.Error(message))
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EventEntity>) {
//        val page = params.key
//        fetchData(page, PAGE_SIZE) {
//            callback.onResult(it, page + 1)
//        }
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EventEntity>) {
//        val page = params.key
//        fetchData(page, PAGE_SIZE) {
//            callback.onResult(it, page - 1)
//        }
//    }
//
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, EventEntity>
//    ) {
//        fetchData(1, PAGE_SIZE) {
//            callback.onResult(it, null, 2)
//        }
//    }
//
//    private fun fetchData(page: Int, pageSize: Int, callback: (List<EventEntity>) -> Unit) {
//        scope.launch(getJobErrorHandler() + supervisorJob) {
//            dataSource.fetchEvents(page, pageSize)
//                .onStart { networkState.postValue(NetworkState(EventState.RUNNING)) }
//                .catch { exception ->
//                    networkState.postValue(
//                        NetworkState(
//                            EventState.FAILED,
//                            message = exception.message ?: "Failed to fetch events"
//                        )
//                    )
//                }.onCompletion {
//                    networkState.postValue(NetworkState(EventState.SUCCESS))
//                }
//                .collect { response ->
//                    val results = response._embedded.events.map {
//                        EventEntity(
//                            it.id,
//                            it.name,
//                            it.images[0].url,
//                            it.sales.public.startDateTime ?: "",
//                            it.sales.public.endDateTime ?: "",
//                            it.promoter?.name ?: "",
//                            it.promoter?.description,
//                            if (it.priceRanges != null) it.priceRanges[0].min else 0.0,
//                            if (it.priceRanges != null) it.priceRanges[0].currency else "",
//                            if (it.priceRanges != null) it.priceRanges[0].type else "Unknown",
//                            it.embedded.venues[0].name ?: "",
//                            it.embedded.venues[0].state.name,
//                            it.embedded.venues[0].boxOfficeInfo?.openHoursDetail ?: "",
//                            it.embedded.venues[0].boxOfficeInfo?.acceptedPaymentDetail ?: "",
//                            it.embedded.venues[0].boxOfficeInfo?.willCallDetail ?: "",
//                            false
//                        )
//
//                    }
//                    //dao.insertAll(results)
//                    Timber.e("${results.size} result size")
//                    callback(results)
//
//                }
//
//
//        }
//    }
//
//    override fun invalidate() {
//        super.invalidate()
//        supervisorJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
//    }
//
//    // PUBLIC API ---
//
//    fun getNetworkState(): LiveData<NetworkState> =
//        networkState


}
