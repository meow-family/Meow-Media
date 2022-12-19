 package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.backgroundTextShadow
import com.octopus.socialnetwork.ui.composable.customImageLoad
import com.octopus.socialnetwork.ui.composable.post.LargPostDetails
import com.octopus.socialnetwork.ui.composable.post.PostImage
import com.octopus.socialnetwork.ui.composable.social_elements.interaction.InteractionGroup
import com.octopus.socialnetwork.ui.composable.social_elements.interaction.InteractionIcon
import com.octopus.socialnetwork.ui.screen.comments.navigateToCommentsScreen
import com.octopus.socialnetwork.ui.screen.post.uistate.PostMainUiState
import com.octopus.socialnetwork.ui.theme.LightBlack_65
import com.octopus.socialnetwork.ui.theme.White50

@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PostContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onLike = viewModel::onClickLike,
        onComment = {navController.navigateToCommentsScreen(postId = state.postDetails.postId, type = "post")},
        onShare = viewModel::onClickShare
    )
}

@Composable
private fun PostContent(
    state: PostMainUiState,
    onClickBack: () -> Unit,
    onLike: () -> Unit,
    onComment: () -> Unit,
    onShare: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .clip(CircleShape)
                .background(color = LightBlack_65)
                .zIndex(1f)
                .size(32.dp)
                .align(alignment = Alignment.TopStart)
        ) {
            IconButton(
                onClick = onClickBack
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = stringResource(id = R.string.icon_arrow_back),
                    tint = White50,
                    modifier = Modifier.size(20.dp).padding(start = 4.dp)
                )
            }

        }
        PostImage(postImage = state.postDetails.postImage)

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
                        icon = if (state.postDetails.isLiked) R.drawable.ic_like_16 else R.drawable.ic_un_like_16,
                        count = state.postDetails.likeCount,
                        onClick = onLike,
                        tint = if (state.postDetails.isLiked) Color.Red else Color.White
                    )
                }, {
                    InteractionIcon(
                        icon = R.drawable.ic_baseline_comment_24,
                        count = state.postDetails.commentCount,
                        onClick = onComment,
                        tint = Color.White
                    )
                }, {
                    InteractionIcon(
                        icon = R.drawable.ic_send,
                        onClick = onShare, tint = Color.White
                    )
                })
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.BottomCenter)
                .backgroundTextShadow()
        ) {
            LargPostDetails(
                profileImage = customImageLoad(state.postDetails.profileAvatar),
                userName = state.postDetails.userName,
                fullName = state.postDetails.fullName,
                postDescription = state.postDetails.postDescription,
            )
        }
    }
}

