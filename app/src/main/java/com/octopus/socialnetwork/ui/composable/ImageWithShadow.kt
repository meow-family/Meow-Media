package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R


@Composable
fun LoginImage(painter: Painter ,modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.background),
            contentScale = ContentScale.Crop,
        )
        ShadowImage()
    }
}
