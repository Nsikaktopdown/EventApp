package com.nsikakthompson.presentation.compose.widget

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nsikakthompson.R


@Preview
@Composable
fun TopBarPreview() {
    TopBar(title = "TopBar", onBackPressed = { })

}

@Preview("Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarDarkPreview() {
    TopBar(title = "TopBar", onBackPressed = { })
}

@Composable
fun TopBar(
    title: String,
    action: @Composable (() -> Unit)? = null,
    onBackPressed: (() -> Unit)? = null
) {

    val backButtonAction: (@Composable () -> Unit)? = if (onBackPressed != null) {
        @Composable { NavigationIcon(navigationOnClick = { onBackPressed() }) }
    } else {
        null
    }
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(fontSize = 24.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        elevation = 0.dp,
        actions = {
            action
        },
        navigationIcon = backButtonAction,
        backgroundColor = MaterialTheme.colors.surface
    )

}


@Composable
private fun NavigationIcon(navigationOnClick: () -> Unit) {
    IconButton(onClick = navigationOnClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            contentDescription = stringResource(R.string.back_arrow)
        )

    }
}