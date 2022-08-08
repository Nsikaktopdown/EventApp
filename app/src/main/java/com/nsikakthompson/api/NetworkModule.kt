package com.nsikakthompson.api

import com.nsikakthompson.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var networkModule = module {
    val HOST = "https://app.ticketmaster.com/discovery/v2/"
    val TAG = "Service"
    val CONNECT_TIMEOUT = 50L
    val READ_TIMEOUT = 50L
    val WRITE_TIMEOUT = 10L

    // lateinit var retrofit: Retrofit
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.build()
    }

    single {
        val  retrofit = Retrofit.Builder()
            .client(get())
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit
    }

    single{
        val api  = (get() as Retrofit).create(ApiService::class.java)
        api
    }
}