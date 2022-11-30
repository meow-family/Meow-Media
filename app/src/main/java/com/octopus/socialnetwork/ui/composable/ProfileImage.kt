package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale


@Composable
fun ProfileImage(painter: Painter, modifier: Modifier) {
    Image(
        painter =painter,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}