package com.octopus.socialnetwork.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = lightPrimaryColor,
    onPrimary = lightOnPrimaryColor,

    secondary = lightSecondaryColor,
    onSecondary =lightOnSecondaryColor,
    secondaryVariant = lightSecondaryVariantColor,

    background = lightBackgroundColor,
    onBackground = White,

    error = errorColor,
)
private val LightColorPalette = lightColors(
    primary = darkPrimaryColor,
    onPrimary = darkOnPrimaryColor,

    secondary = darkSecondaryColor,
    onSecondary =darkOnSecondaryColor,
    secondaryVariant = darkSecondaryVariantColor,

    background = darkBackgroundColor,
    onBackground = White80,

    error = errorColor,
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