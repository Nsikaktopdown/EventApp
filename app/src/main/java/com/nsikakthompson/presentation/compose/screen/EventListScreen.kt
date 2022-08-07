package com.nsikakthompson.presentation.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import com.nsikakthompson.presentation.viewmodel.EventViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel


@Composable
fun EventListScreen(
    eventViewModel: EventViewModel = getViewModel(),
    navController: NavController,
) {

    val uiState = eventViewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.popular_event), action = {
                WishListCounter()
            })
        }) {

        LoadingContent(
            empty = when (uiState) {
                is EventUIState.HasEvent -> false
                is EventUIState.NoEvent -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            loading = uiState.isLoading,
            onRefresh = {
                eventViewModel.refreshEvents()
            },
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


@Preview(showBackground = false)
@Composable
fun WishListCounter() {
    BadgedBox(badge = { Badge { Text("8") } }) {
        Icon(
            Icons.Filled.Favorite,
            tint = Color.White,
            contentDescription = "Favorite"
        )
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

