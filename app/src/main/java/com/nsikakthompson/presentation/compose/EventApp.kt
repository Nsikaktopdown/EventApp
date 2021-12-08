package com.nsikakthompson.presentation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.nsikakthompson.presentation.compose.navigation.AppNavigationGraph
import com.nsikakthompson.presentation.compose.tools.LayoutTheme


@Composable
fun EventApp() {
    LayoutTheme {
        ProvideWindowInsets {
            val navController = rememberNavController()
            AppNavigationGraph(navController = navController)
        }
    }
}