package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ImageNetwork
import com.octopus.socialnetwork.ui.screen.profile.state.uistate.ProfilePostUiState


@Composable
fun ProfilePostItem(
    post: ProfilePostUiState,
    onClickPost: (Int,Int) -> Unit
) {

    Box(
        modifier = Modifier
            .height(130.dp)
            .padding(horizontal = 8.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable { onClickPost(post.postId, post.postOwnerId) },
    ) {

        ImageNetwork(
            modifier = Modifier.fillMaxSize(),
            imageUrl = post.postImage,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.this_is_post_image)
        )
    }


}