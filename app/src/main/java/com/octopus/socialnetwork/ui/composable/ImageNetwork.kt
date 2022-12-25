package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.octopus.socialnetwork.R
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ImageNetwork(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    GlideImage(
        modifier = modifier,
        imageModel = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = {
            Box {
                Loading()
            }
        },
        failure = {
            painterResource(id = R.drawable.error_image)
        }
    )
}