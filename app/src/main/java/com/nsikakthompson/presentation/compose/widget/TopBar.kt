package com.nsikakthompson.presentation.compose.widget

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nsikakthompson.R

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "Popular Event",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.W600))
        },
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_active_bookmark),
                    contentDescription = "Search")
            }
        }
    )

}