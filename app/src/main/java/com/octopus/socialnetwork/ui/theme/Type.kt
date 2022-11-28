package com.octopus.socialnetwork.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R

private val Poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.W300),
    Font(R.font.poppins_regular, FontWeight.W400),
    Font(R.font.poppins_medium, FontWeight.W500),
    Font(R.font.poppins_bold, FontWeight.W600),
)

val PoppinsTypography = Typography(

    h1 = TextStyle(
        fontFamily = Poppins,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = Poppins,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = Poppins,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = Poppins,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = Poppins,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Poppins,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Poppins,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Poppins,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    ),

    caption = TextStyle(
        fontFamily = Poppins,
        fontSize = 12.sp
    ),

    overline = TextStyle(
        fontFamily = Poppins,
        fontSize = 10.sp
    ),
)