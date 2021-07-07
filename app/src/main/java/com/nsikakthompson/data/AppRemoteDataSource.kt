package com.nsikakthompson.data

import com.nsikakthompson.api.ApiService
import com.nsikakthompson.api.BaseDataSource

class AppRemoteDataSource(private var apiService: ApiService) : BaseDataSource() {
    private val API_KEY = "A4yqiIGWfj9lF4xbP4lwlXA0NRwnckkx"
    suspend fun fetchEvents(page: Int? = null, size: Int? = null) = getResult {
        apiService.getEvent(page, size, API_KEY)
    }
}