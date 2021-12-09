package com.nsikakthompson.presentation.compose.screen

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nsikakthompson.presentation.compose.widget.TopBar
import com.nsikakthompson.presentation.viewmodel.EventUIState
import com.nsikakthompson.presentation.viewmodel.EventViewModel

@Composable
fun EventRoute(
    eventViewModel: EventViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
){
    val state by eventViewModel.uiState.collectAsState()
    EventListScreen(topBar = { TopBar() },
        uiState =  state,
        scaffoldState = scaffoldState, onRefreshEvents= {eventViewModel.refreshEvents()}
    )
}