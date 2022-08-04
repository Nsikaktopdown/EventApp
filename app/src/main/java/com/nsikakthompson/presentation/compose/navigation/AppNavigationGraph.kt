package com.nsikakthompson.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nsikakthompson.presentation.compose.screen.EventDetailScreen
import com.nsikakthompson.presentation.compose.screen.EventRoute
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.EVENT_LIST_ROUTE,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppDestinations.EVENT_LIST_ROUTE) {
            EventRoute(
                eventViewModel = getViewModel(),
                navController = navController
            )
        }
        composable(AppDestinations.EVENT_DETAILS){
            EventDetailScreen(navController = navController)
        }
    }
}