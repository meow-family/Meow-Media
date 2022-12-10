package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.interaction.InteractionIcon
import com.octopus.socialnetwork.ui.composable.post.LargPostDetails
import com.octopus.socialnetwork.ui.composable.post.PostImage
import com.octopus.socialnetwork.ui.composable.shadowLightBlack
import com.octopus.socialnetwork.ui.screen.post.uistate.PostMainUiState

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
        onComment = viewModel::onClickComment,
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

        PostImage(postImage = rememberAsyncImagePainter(model = state.postDetails.postImage))

        Column(
            modifier = Modifier
                .wrapContentHeight()
                // .height(210.dp)
                .align(alignment = Alignment.CenterEnd)
                .width(48.dp),
            verticalArrangement = Arrangement.SpaceAround


        ) {
                    InteractionIcon(
                        icon = painterResource(id = R.drawable.ic_like),
                        count = state.postDetails.likeCount,
                        tint = if (state.postDetails.isLiked) Color.Red else  Color.White,
                        onClick = onLike
                    )

                    InteractionIcon(
                        icon = painterResource(id = R.drawable.ic_baseline_comment_24),
                        count = state.postDetails.commentCount,
                        tint = Color.White,
                        onClick = onComment
                    )
                    InteractionIcon(
                        icon = painterResource(id = R.drawable.ic_baseline_share_24),
                        tint = Color.White,
                        onClick = onShare
                    )


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.BottomCenter)
                .shadowLightBlack()
        ) {
            LargPostDetails(
                profileImage = rememberAsyncImagePainter(model = state.postDetails.profileAvatar),
                userName = state.postDetails.userName,
                fullName = state.postDetails.fullName,
                postDescription = state.postDetails.postDescription,
            )
        }
    }
}
@Preview
@Composable
fun foo(){
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(24.dp).clip(CircleShape).shadowLightBlack()
            //.align(Alignment.TopStart)

    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_back),
            contentDescription = "print",
            tint = Color.Black
        )
    }
}