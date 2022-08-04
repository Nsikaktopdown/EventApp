package com.nsikakthompson.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events.json")
    suspend fun getEvent(@Query("page") page: Int? = null,
                         @Query("size")size: Int? = null,
                         @Query("apikey")  apiKey: String) : EventResponse

}