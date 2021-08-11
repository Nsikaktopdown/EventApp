package com.nsikakthompson.data

import com.nsikakthompson.api.ApiService
import com.nsikakthompson.api.BaseDataSource
import com.nsikakthompson.api.Config
import com.nsikakthompson.api.EventResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRemoteDataSource(private var apiService: ApiService) : BaseDataSource() {
    suspend fun fetchEvents(page: Int? = null, size: Int? = null) = flow<EventResponse> {
        val events = apiService.getEvent(page, size, Config.API_KEY)
        emit(events)
    }
        .flowOn(Dispatchers.IO)

}