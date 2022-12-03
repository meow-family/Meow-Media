package com.octopus.socialnetwork.ui.composable.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Composable
fun AppName(text: String){
    Text(
        text = text,
        fontSize = 24.sp,
        color = Gray900_2 ,
        fontFamily = FontFamily.Cursive
    )
}