package com.nsikakthompson.data

import com.nsikakthompson.App
import com.nsikakthompson.cache.AppDatabase
import com.nsikakthompson.domain.EventRepository
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
        var coroutineScope = CoroutineScope(Dispatchers.Default)
        coroutineScope
    }

    single{
         AppRemoteDataSource(get())
    }
    single {
       AppPageDataSourceFactory(get(), get(), get())
    }
    single{
        var eventRepository: EventRepository = EventRepositoryImpl(get(), get())
        eventRepository
    }
}