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

    private val PAGE_SIZE = 8
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
                        it.images[2].url,
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

}