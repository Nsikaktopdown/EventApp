package com.nsikakthompson.presentation.compose.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun EventRoute(
    navController: NavController
) {
    EventListScreen(
        navController = navController
    )
}

