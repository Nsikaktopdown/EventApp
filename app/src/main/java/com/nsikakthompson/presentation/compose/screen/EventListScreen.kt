package com.nsikakthompson.presentation.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.presentation.compose.widget.EventItem
import com.nsikakthompson.presentation.viewmodel.EventUIState


@Composable
fun EventListScreen(
    topBar: @Composable () -> Unit,
    uiState: EventUIState,
    scaffoldState: ScaffoldState,
    onRefreshPosts: () -> Unit
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            topBar()
        }) {

        LoadingContent(
            empty = when (uiState) {
                is EventUIState.HasEvent -> false
                is EventUIState.NoEvent -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            loading = uiState.isLoading,
            onRefresh = onRefreshPosts,
            content = {
                when (uiState) {
                    is EventUIState.HasEvent -> {
                        EventList(events = uiState.eventFeed)
                    }
                    is EventUIState.NoEvent -> {
                        Text(uiState.errorMessage)
                    }
                }
            })


    }
}


@Composable
fun EventList(
    events: List<EventEntity>
) {
    val scrollListState = rememberLazyListState()
    LazyColumn(state = scrollListState) {
        items(count = events.size) { index ->
            EventItem(events[index])
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

/**
 * Display an initial empty state or swipe to refresh content.
 *
 * @param empty (state) when true, display [emptyContent]
 * @param emptyContent (slot) the content to display for the empty state
 * @param loading (state) when true, display a loading spinner over [content]
 * @param onRefresh (event) event to request refresh
 * @param content (slot) the main content to show
 */
@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(loading),
            onRefresh = onRefresh,
            content = content,
        )
    }
}

/*
* Full screen circular progress indicator
*/
@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}