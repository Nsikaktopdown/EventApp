package com.nsikakthompson.presentation.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nsikakthompson.R
import com.nsikakthompson.api.ApiService
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.formatDate
import com.nsikakthompson.formatTime
import com.nsikakthompson.presentation.compose.data.FakeEventProvider
import com.nsikakthompson.presentation.compose.widget.DetailItem
import com.nsikakthompson.presentation.compose.widget.TopBar
import retrofit2.HttpException
import java.io.IOException
import java.lang.RuntimeException


class EventProvider : PreviewParameterProvider<EventEntity> {
    override val values: Sequence<EventEntity>
        get() = sequenceOf(
            FakeEventProvider.getEvent
        )
}


@Preview
@Composable
fun EventDetailScreenPreview(
    @PreviewParameter(EventProvider::class) event: EventEntity
) {
    EventDetailScreen(event = event, navController = rememberNavController())
}

@Composable
fun EventDetailScreen(
    event: EventEntity = FakeEventProvider.getEvent,
    navController: NavController
) {

    Scaffold(
        topBar = {
            TopBar(title = event.name, onBackPressed = { navController.popBackStack() })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
            ) {
                Icon(Icons.Filled.FavoriteBorder, "bookmark")
            }
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.event),
                    contentDescription = "",
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                DetailItem(
                    image = R.drawable.ic_time, headerTitle = event.openHoursDetail.formatDate(),
                    description = "${event.startDateTime.formatTime()} - ${event.endDateTime.formatTime()}"
                )
                DetailItem(
                    image = R.drawable.ic_ticket, headerTitle = "$${event.price}",
                    description = event.ticketType
                )
                event.promoterName?.let { name ->
                    DetailItem(
                        image = R.drawable.ic_person, headerTitle = name,
                        description = event.promoterDesc ?: ""
                    )
                }
                DetailItem(
                    image = R.drawable.ic_venue, headerTitle = event.venueName,
                    description = event.venueState
                )
            }
        }
    }

}

