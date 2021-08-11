package com.nsikakthompson.data

import com.nsikakthompson.api.ApiService
import com.nsikakthompson.api.BaseDataSource
import com.nsikakthompson.api.EventResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRemoteDataSource(private var apiService: ApiService) : BaseDataSource() {
    private val API_KEY = "A4yqiIGWfj9lF4xbP4lwlXA0NRwnckkx"
    suspend fun fetchEvents(page: Int? = null, size: Int? = null) = flow<EventResponse> {
        val events = apiService.getEvent(page, size, API_KEY)
        emit(events)
    }
        .flowOn(Dispatchers.IO)

}