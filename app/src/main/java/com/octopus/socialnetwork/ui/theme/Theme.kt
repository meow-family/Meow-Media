package com.octopus.socialnetwork.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Red600,
    onPrimary = White50,
    background = Gray900_1,
    onBackground = White50,
    secondaryVariant = Gray700,
    secondary = Gray500,
    error = Red500,
)
private val LightColorPalette = lightColors(
    primary = Red600,
    onPrimary = White50,
    background = White50,
    onBackground = Gray900_2,
    secondary = Gray500,
    onSecondary = Gray900_2,
    error = Red500
)

@Composable
fun SocialNetworkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    val colorsTheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    systemUiController.setSystemBarsColor(
        color = colorsTheme.background,
        darkIcons = !darkTheme
    )

    MaterialTheme(
        colors = colorsTheme,
        typography = PoppinsTypography,
        shapes = Shapes,
        content = content
    )

}