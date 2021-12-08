package com.nsikakthompson.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsikakthompson.R
import com.nsikakthompson.api.networkModule
import com.nsikakthompson.data.dataModule
import com.nsikakthompson.presentation.compose.EventApp
import com.nsikakthompson.presentation.compose.screen.EventListScreen
import com.nsikakthompson.presentation.compose.tools.LayoutTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           EventApp()
        }

    }

}