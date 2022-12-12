package com.octopus.socialnetwork.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.octopus.socialnetwork.R

private val Poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.W300),
    Font(R.font.poppins_regular, FontWeight.W400),
    Font(R.font.poppins_medium, FontWeight.W500),
    Font(R.font.poppins_bold, FontWeight.W600),
)

val PoppinsTypography = Typography(
    Poppins,

    h4 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = fontSizesTitleExtra
    ),

    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = fontSizesSecondary
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = fontSizePrimary
    ),

    subtitle2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = fontSizesCaption
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = fontSizesSecondary
    ),
    body2 = TextStyle(
        fontSize = fontSizePrimary
    ),

    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = fontSizePrimary
    ),

    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = fontSizesCaption
    ),

    overline = TextStyle(
        fontSize = fontSizesSmall,
    ),
)