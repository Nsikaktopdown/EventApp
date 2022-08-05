package com.nsikakthompson

import android.app.Application
import com.nsikakthompson.api.networkModule
import com.nsikakthompson.data.dataModule
import com.nsikakthompson.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(listOf(
                dataModule, networkModule, presentationModule
            ))
        }
    }

}