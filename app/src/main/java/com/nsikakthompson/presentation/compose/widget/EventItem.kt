package com.nsikakthompson.presentation.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsikakthompson.R
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.formatDate
import com.nsikakthompson.formatTime
import com.nsikakthompson.presentation.compose.tools.LayoutTheme


@Composable
fun EventItem(event: EventEntity) {
    LayoutTheme() {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.White
        ) {
            Row(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.event),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                )
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        event.name,
                        style = MaterialTheme.typography.body1
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
}