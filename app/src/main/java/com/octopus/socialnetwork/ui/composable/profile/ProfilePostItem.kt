package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.profile.uistate.ProfilePostUiState


@Composable
fun ProfilePostItem(
    post: ProfilePostUiState,
    onClickPost: (Int,Int) -> Unit
) {
    Image(
        modifier = Modifier
            .height(130.dp)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp) )
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable { onClickPost(post.postId, post.postOwnerId) },
        contentScale = ContentScale.Crop,
        painter = rememberAsyncImagePainter(model = post.postImage),
        contentDescription = stringResource(R.string.this_is_post_image)
    )
}