package com.nsikakthompson.presentation.compose.screen

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nsikakthompson.R
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.presentation.compose.navigation.AppDestinations
import com.nsikakthompson.presentation.compose.widget.EventItem
import com.nsikakthompson.presentation.compose.widget.EventListDivider
import com.nsikakthompson.presentation.compose.widget.TopBar
import com.nsikakthompson.presentation.viewmodel.EventUIState
import kotlinx.coroutines.flow.Flow


@Composable
fun EventListScreen(
    uiState: EventUIState,
    scaffoldState: ScaffoldState,
    navController: NavController,
    onRefreshEvents: () -> Unit
) {
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopBar(title = stringResource(id = R.string.popular_event))
        }) {

        LoadingContent(
            empty = when (uiState) {
                is EventUIState.HasEvent -> false
                is EventUIState.NoEvent -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            loading = uiState.isLoading,
            onRefresh = onRefreshEvents,
            content = {
                when (uiState) {
                    is EventUIState.HasEvent -> {
                        EventList(
                            eventList = uiState.eventFeed,
                            navController = navController
                        )
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
    eventList: Flow<PagingData<EventEntity>>,
    navController: NavController
) {
    val events: LazyPagingItems<EventEntity> = eventList.collectAsLazyPagingItems()
    val scrollListState = rememberLazyListState()
    LazyColumn(
        state = scrollListState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(events.itemCount) { index ->
            events.let {
                events[index]?.let { event ->
                    EventItem(event, onItemClick = {
                        navController.navigate(AppDestinations.EVENT_DETAILS + event.toString())
                    })
                }
            }
            EventListDivider()
        }

        events.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        FullScreenLoading()
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        Text("Something went wrong")
                    }
                }
            }
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
        content()
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

