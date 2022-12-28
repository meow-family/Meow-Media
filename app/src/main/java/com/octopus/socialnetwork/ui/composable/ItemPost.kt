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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.home.SmallPostDetails
import com.octopus.socialnetwork.ui.composable.post.setLikeColor
import com.octopus.socialnetwork.ui.composable.post.setLikeSize
import com.octopus.socialnetwork.ui.composable.social_elements.interaction.InteractionGroup
import com.octopus.socialnetwork.ui.composable.social_elements.interaction.InteractionIcon
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import com.octopus.socialnetwork.ui.util.playMeowSound

@Composable
fun ItemPost(
    post: PostUiState,
    onClickPost: (Int, Int) -> Unit,
    onLike: (Int, Int, Boolean) -> Unit,
    onComment: (Int) -> Unit,
    onShare: () -> Unit
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .height(450.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .shadow(4.dp)
            .clickable { onClickPost(post.postId, post.ownerId) }
    ) {

        ImageNetwork(
            modifier = Modifier.fillMaxSize(),
            imageUrl = post.postImage,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.this_is_post_image)
        )

        Card(
            modifier = Modifier
                .height(210.dp)
                .align(alignment = Alignment.CenterEnd)
                .width(48.dp),
            elevation = 0.dp,
            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            backgroundColor = Color.Transparent,
        ) {
            InteractionGroup(
                interactions =
                listOf({
                    InteractionIcon(
                        icon = R.drawable.ic_cat_foot,
                        count = post.likeCount,
                        onClick = {
                            onLike(post.postId, post.likeCount.toInt(), post.isLiked)
                            playMeowSound(post.isLiked, context)
                        },
                        tint = setLikeColor(post.isLiked),
                        modifier = Modifier.size(setLikeSize(post.isLiked))
                    )
                }, {
                    InteractionIcon(
                        icon = R.drawable.ic_baseline_comment_24,
                        count = post.commentCount,
                        onClick = { onComment(post.postId) },
                        tint = Color.White
                    )
                }
                )
            )

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.BottomCenter),
            elevation = 0.dp,
            shape = AbsoluteRoundedCornerShape(bottomLeft = 16.dp, bottomRight = 16.dp),
            backgroundColor = Color.Transparent

        ) {
            SmallPostDetails(post = post)
        }

    }
}

