package com.nsikakthompson.api

import com.nsikakthompson.data.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events.json")
    suspend fun getEvent(@Query("page") page: Int? = null,
                         @Query("size")size: Int? = null,
                         @Query("apiKey")  apiKey: String) : Result<EventResponse>
}