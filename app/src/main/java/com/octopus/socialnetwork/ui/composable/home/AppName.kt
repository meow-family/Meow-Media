package com.octopus.socialnetwork.ui.composable.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

@Composable
fun AppName(text: String){
    Text(
        text = text,
        fontSize = 26.sp,
        color = MaterialTheme.colors.textPrimaryColor,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.padding(start = 16.dp)

    )
}