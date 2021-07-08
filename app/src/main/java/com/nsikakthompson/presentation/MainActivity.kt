package com.nsikakthompson.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsikakthompson.R
import com.nsikakthompson.api.networkModule
import com.nsikakthompson.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(
                dataModule, networkModule, presentationModule
            ))
        }
    }
}