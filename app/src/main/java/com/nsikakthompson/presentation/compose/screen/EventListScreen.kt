package com.nsikakthompson.presentation.compose.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nsikakthompson.presentation.compose.widget.EventItem

@Preview
@Composable
fun EventListScreen(){
    val scrollListState = rememberLazyListState()
    LazyColumn(state = scrollListState ){
        items(count = 4){
            EventItem()
            Spacer(modifier = Modifier.height(5.dp))
        }

    }
}

