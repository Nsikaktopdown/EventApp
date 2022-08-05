package com.nsikakthompson.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.presentation.compose.data.EventType
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
        composable(AppDestinations.EVENT_DETAILS + "{event}",
        arguments = listOf(navArgument("event") {type = EventType()})){ backStackEntry ->
            backStackEntry.arguments?.getParcelable<EventEntity>("event")
                ?.let { EventDetailScreen(event = it,navController = navController) }
        }
    }
}