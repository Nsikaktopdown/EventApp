package com.nsikakthompson.presentation.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.formatDate
import com.nsikakthompson.formatTime
import com.nsikakthompson.presentation.compose.navigation.AppDestinations
import com.nsikakthompson.presentation.compose.tools.LayoutTheme


class EventProvider : PreviewParameterProvider<EventEntity> {
    override val values: Sequence<EventEntity>
        get() = listOf(
            EventEntity(
                id = "",
                name = "My event for good",
                imageUrl = "https://s1.ticketm.net/dam/a/0c4/725d27e6-8984-456e-8461-13e1b71bc0c4_1325051_TABLET_LANDSCAPE_LARGE_16_9.jpg",
                startDateTime = "2021-05-13T15:00:00Z",
                endDateTime = "2021-05-13T15:00:00Z",
                promoterName = "blalal",
                promoterDesc = "",
                price = 100.0,
                currency = "",
                ticketType = "",
                venueName = "",
                venueState = "",
                openHoursDetail = "",
                acceptedPaymentDetail = "",
                willCallDetail = "",
                false
            )
        ).asSequence()
}

@Preview
@Composable
fun EventItemPreview(@PreviewParameter(EventProvider::class) event: EventEntity) {
    EventItem(event = event, navController = rememberNavController())
}

@Composable
fun EventItem(
    event: EventEntity,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(AppDestinations.EVENT_DETAILS)
            },
        elevation = 2.dp
    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(event.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    event.name,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    event.startDateTime.formatDate(),
                    style = MaterialTheme.typography.caption
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    "${event.startDateTime.formatTime()} - ${event.endDateTime.formatTime()}",
                    style = MaterialTheme.typography.caption
                )

            }
        }
    }
}