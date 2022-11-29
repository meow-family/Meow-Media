package com.octopus.socialnetwork.ui.screen.post.composibale

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserDetails() {
    Column {
        Text(
            text = "Moataz",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "@moataz_h",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PostDescription() {
    Text(
        modifier = Modifier.padding(end = 22.dp),
        text = "This is my rabbit it is so cute her name kiu kgkg gkmggrkmkgmkmgkmkgrkmgkrgrgrggr lglorg but before 4 years she is dead (I am sad for that, and I still miss her)",
        color = Color.White, fontSize = 14.sp,
        maxLines = 3,
    )
}