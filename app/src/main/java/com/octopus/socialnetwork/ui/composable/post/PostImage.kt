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
fun PostImage(postImage: Painter) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = postImage,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.this_is_post_image)
    )
}

