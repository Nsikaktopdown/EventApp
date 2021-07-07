package com.nsikakthompson.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("https://app.ticketmaster.com/discovery/v2/events.json")
    suspend fun getEvent(@Query("page") page: Int? = null,
                         @Query("size")size: Int? = null,
                         @Query("apiKey")  apiKey: String) : Response<List<EventResponse>>
}