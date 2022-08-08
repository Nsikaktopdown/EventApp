package com.nsikakthompson.presentation.compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.nsikakthompson.R
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.formatTime


@Preview(showBackground = true)
@Composable
fun DetailItemPreview() {
    DetailItem(
        image = R.drawable.ic_time,
        headerTitle = "Header",
        description = "Description"
    )
}

@Composable
fun DetailItem(
    modifier: Modifier = Modifier.padding(10.dp),
    image: Int,
    headerTitle: String,
    description: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "time",
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
                .align(alignment = Alignment.Top)
        )
        Spacer(Modifier.width(20.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = headerTitle,
                style = MaterialTheme.typography.body1,
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.caption,
                )
            }

        }
    }
}