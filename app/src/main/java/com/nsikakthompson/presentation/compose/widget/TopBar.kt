package com.nsikakthompson.presentation.compose.widget

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import com.nsikakthompson.presentation.compose.screen.WishListCounter
import com.nsikakthompson.presentation.compose.tools.LayoutTheme


@Preview
@Composable
fun TopBarPreview() {
    LayoutTheme {
        TopBar(title = "TopBar", onBackPressed = { }, actions = {
            WishListCounter()
        })
    }

}

@Preview("Dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarDarkPreview() {
   LayoutTheme {
       TopBar(title = "TopBar", onBackPressed = { }, actions = {
           WishListCounter()
       })
   }
}

@Composable
fun TopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit? = { },
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
                style = MaterialTheme.typography.body1.copy(fontSize = 20.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        elevation = 0.dp,
        actions = {
            actions.invoke(this)
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