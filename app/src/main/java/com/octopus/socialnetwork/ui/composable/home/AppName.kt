package com.octopus.socialnetwork.ui.composable.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.theme.Gray900_2
import com.octopus.socialnetwork.ui.theme.textSecondaryColor

@Composable
fun AppName(text: String){
    Text(
        text = text,
        fontSize = 24.sp,
        color = MaterialTheme.colors.textSecondaryColor,
        fontFamily = FontFamily.Cursive

    )
}