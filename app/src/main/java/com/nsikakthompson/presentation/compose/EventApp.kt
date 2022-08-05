package com.nsikakthompson.presentation.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nsikakthompson.presentation.compose.navigation.AppNavigationGraph
import com.nsikakthompson.presentation.compose.tools.LayoutTheme


@Composable
fun EventApp() {
    LayoutTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }
            val navController = rememberNavController()
            AppNavigationGraph(navController = navController)
        }
    }
}