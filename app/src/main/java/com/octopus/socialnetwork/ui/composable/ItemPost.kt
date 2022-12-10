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
import com.octopus.socialnetwork.R
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.ui.composable.home.SmallPostDetails
import com.octopus.socialnetwork.ui.composable.interaction.InteractionGroup
import com.octopus.socialnetwork.ui.composable.interaction.InteractionIcon
import com.octopus.socialnetwork.ui.composable.post.PostAction
import com.octopus.socialnetwork.ui.composable.post.PostImage
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState

@Composable
fun ItemPost(
    post: PostUiState,
    onClickPost: (Int, Int) -> Unit,
    onLike: () -> Unit,
    onComment: () -> Unit,
    onShare: () -> Unit
) {

    Box(
        modifier = Modifier
            .height(380.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable { onClickPost(post.postId, post.ownerId) }
    ) {

        PostImage(postImage = rememberAsyncImagePainter(model = post.postImage))


        Card(
            modifier = Modifier
                .height(210.dp)
                .align(alignment = Alignment.CenterEnd)
                .width(48.dp),
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            backgroundColor = Color.Transparent,
        ) {
            InteractionGroup(
                interactions =
                listOf({
                    InteractionIcon(
                        icon = R.drawable.ic_like,
                        count = post.likeCount,
                        onClick = onLike,
                        tint = if (post.isLiked) Color.Red else Color.White
                    )
                }, {
                    InteractionIcon(
                        icon = R.drawable.ic_baseline_comment_24,
                        count =  post.commentCount,
                        onClick = onComment,
                        tint = Color.White
                    )
                }, {
                    InteractionIcon(
                        icon = R.drawable.ic_send,
                        onClick = onShare, tint = Color.White)
                })
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

