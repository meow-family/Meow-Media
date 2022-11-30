package com.octopus.socialnetwork.ui.composable.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R

@Composable
fun PostImage(painter: Painter) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter =painter,
        contentScale = ContentScale.FillBounds,
        contentDescription = stringResource(R.string.description)
    )
}

