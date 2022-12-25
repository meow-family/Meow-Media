package com.octopus.socialnetwork.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R


@Composable
fun ProfileImage(imageUrl: String, modifier: Modifier) {
    ImageNetwork(
        imageUrl = imageUrl,
        contentDescription = stringResource(R.string.profile_image),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}