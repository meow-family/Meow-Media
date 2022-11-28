package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun TextSignUpButton(
    text:String,
    onClick: () -> Unit
){
    Text(
        modifier = Modifier.clickable { onClick },
        text = text,
        fontSize = 14.sp,
        color = Color.Red,
        fontFamily = FontFamily.SansSerif,
    )
}