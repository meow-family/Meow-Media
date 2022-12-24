package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.octopus.socialnetwork.R
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ImageNetwork(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String,
    contentScale: ContentScale,
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