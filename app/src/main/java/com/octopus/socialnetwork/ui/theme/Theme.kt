package com.octopus.socialnetwork.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(

    primary = darkPrimaryColor,
    onPrimary = darkOnPrimaryColor,

    secondary = darkSecondaryColor,
    onSecondary = darkOnSecondaryColor,
    secondaryVariant = darkSecondaryVariantColor,

    background = darkBackgroundColor,
    onBackground = White80,

    error = errorColor,
)
private val LightColorPalette = lightColors(
    primary = lightPrimaryColor,
    onPrimary = lightOnPrimaryColor,

    secondary = lightSecondaryColor,
    onSecondary = lightOnSecondaryColor,
    secondaryVariant = lightSecondaryVariantColor,

    background = lightBackgroundColor,
    onBackground = Black,

    error = errorColor,
)


val Colors.textPrimaryColor: Color
    get() = if (isLight) lightTextPrimaryColor else darkTextPrimaryColor

val Colors.textSecondaryColor: Color
    get() = if (isLight) lightSecondaryColor else darkSecondaryColor
val Colors.textThirdColor: Color
    get() = if (isLight) lightTextThirdColor else darkTextThirdColor

val Colors.outLine: Color
    get() = if (isLight) lightOutLineColor else darkOutLineColor


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