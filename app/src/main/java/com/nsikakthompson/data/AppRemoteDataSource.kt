package com.nsikakthompson.data

import com.nsikakthompson.api.ApiService
import com.nsikakthompson.api.Config

class AppRemoteDataSource(private var apiService: ApiService)  {
    suspend fun fetchEvents(page: Int? = null, size: Int? = null) =
        apiService.getEvent(page, size, Config.API_KEY)


}