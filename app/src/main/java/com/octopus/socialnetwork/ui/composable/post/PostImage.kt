package com.octopus.socialnetwork.ui.composable.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ImageNetwork

@Composable
fun PostImage(postImage: String) {
    ImageNetwork(
        modifier = Modifier.fillMaxSize(),
        imageUrl = postImage,
        contentDescription = stringResource(R.string.this_is_post_image)
    )

}