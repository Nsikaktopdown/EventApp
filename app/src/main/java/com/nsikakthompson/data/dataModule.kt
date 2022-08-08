package com.nsikakthompson.data

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.nsikakthompson.App
import com.nsikakthompson.cache.AppDatabase
import com.nsikakthompson.domain.usecase.*
import com.nsikakthompson.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


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
        var eventRepository: EventRepository = EventRepositoryImpl(get())
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
        EventPagedDataSource(get())
    }
    single {
        var getEventUseCase = GetEventListUseCase(get())
        getEventUseCase
    }

    single {
        var addToWishListUseCase = AddToWishListUseCase(get())
        addToWishListUseCase
    }

    single {
        var removeFromWishListUseCase = RemoveFromWishListUseCase(get())
        removeFromWishListUseCase
    }

    single {
        var getEventByIdUseCase = GetEventIsWishedUseCase(get())
        getEventByIdUseCase
    }
    single {
        var getWishListCountUseCase = GetWishListCountUseCase(get())
        getWishListCountUseCase
    }


}