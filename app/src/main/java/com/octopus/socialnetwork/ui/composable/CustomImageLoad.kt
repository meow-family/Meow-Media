package com.octopus.socialnetwork.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.octopus.socialnetwork.R

@Composable
fun customImageLoad(imageUrl: String): Painter {
    return rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl).crossfade(100).build(),
        placeholder = painterResource(id = R.drawable.loading_image),
        error = painterResource(id = R.drawable.error_image),
    )
}