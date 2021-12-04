package com.nsikakthompson.presentation.compose.tools

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nsikakthompson.R

val OpenSans = FontFamily(
    Font(R.font.opensans_regular),
    Font(R.font.opensans_semibold, FontWeight.W500),
    Font(R.font.opensans_bold, FontWeight.Bold)
)



private val DarkColorPalette = darkColors(
    primary = Color(0xFF000000),
    primaryVariant = Color(0xFF000000),
    secondary = Color(0xFF000000)
)

private val LightColorPalette = lightColors(
    primary = purple200,
    primaryVariant = purple500,
    secondary = purple200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

// Set of Material typography styles to start with
val typography = Typography(
    h4 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        color = textDark
    ),
    body2 = TextStyle(
        fontFamily = OpenSans,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = textLight
    ),
    overline = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)

val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun LayoutTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
