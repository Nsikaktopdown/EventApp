package com.nsikakthompson.data

import android.content.Context
import com.nsikakthompson.App
import com.nsikakthompson.cache.AppDatabase
import com.nsikakthompson.domain.EventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import android.net.NetworkInfo

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.nsikakthompson.domain.usecase.*
import com.nsikakthompson.utils.DispatcherProvider


var dataModule = module {

    single {
        App()
    }

    single {
        var database = AppDatabase.getInstance(get())
        database
    }

    single {
        var dao = (get() as AppDatabase).eventDao()
        dao
    }

    single {
        var dispatcherProvider = DispatcherProvider()
        dispatcherProvider
    }

    single {
        var coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope
    }

    single {
        var appRemoteDataSource = AppRemoteDataSource(get())
        appRemoteDataSource
    }


    single {
        AppPageDataSourceFactory(get(), get(), get())
    }

    single {
        var eventRepository: EventRepository = EventRepositoryImpl(get(), get())
        eventRepository
    }

    single {
        val connectivityManager =
            getSystemService(
                androidContext(),
                ConnectivityManager::class.java
            ) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        var isNetworkAvailable = activeNetworkInfo != null && activeNetworkInfo.isConnected
        isNetworkAvailable
    }

    single {
        var getEventUseCase = GetEventListUseCase(get(), get())
        getEventUseCase
    }

    single {
        var addToWishListUseCase = AddToWishListUseCase(get(), get())
        addToWishListUseCase
    }

    single {
        var removeFromWishListUseCase = RemoveFromWishListUseCase(get(), get())
        removeFromWishListUseCase
    }

    single {
        var getEventByIdUseCase = GetEventByIdUseCase(get(), get())
        getEventByIdUseCase
    }
    single {
        var getWishListCountUseCase = GetWishListCountUseCase(get(), get())
        getWishListCountUseCase
    }


}