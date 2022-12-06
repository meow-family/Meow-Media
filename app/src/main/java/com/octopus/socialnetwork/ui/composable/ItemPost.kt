package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.ui.composable.home.SmallPostDetails
import com.octopus.socialnetwork.ui.composable.post.PostAction
import com.octopus.socialnetwork.ui.composable.post.PostImage
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import com.octopus.socialnetwork.ui.theme.LightBlack_65

@Composable
fun ItemPost(
    post: PostUiState,
    onClickPost: (Int,Int)-> Unit,
    onLike: () -> Unit,
    onComment: () -> Unit,
    onShare: () -> Unit
) {

    Box(
        modifier = Modifier
            .height(380.dp)
            .clip(shape = RoundedCornerShape(16.dp)).clickable { onClickPost(post.postId,post.ownerId) }
    ) {

        PostImage(postImage = rememberAsyncImagePainter(model = post.postImage))


        Card(
            modifier = Modifier
                .height(210.dp)
                .align(alignment = Alignment.TopEnd)
                .width(48.dp),
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            backgroundColor = LightBlack_65,
        ) {
            PostAction(
                likeCount = post.likeCount,
                commentCount = post.commentCount,
                onLike = onLike,
                onComment = onComment,
                onShare = onShare,
                modifier = Modifier.size(18.dp)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .align(alignment = Alignment.BottomCenter),
            elevation = 0.dp,
            shape = AbsoluteRoundedCornerShape(bottomLeft = 16.dp, bottomRight = 16.dp),
            backgroundColor = Color.Transparent

        ) {
            SmallPostDetails(post = post)

        }




    }
}

