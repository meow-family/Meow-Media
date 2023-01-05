package com.octopus.socialnetwork.ui.screen.post

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.LoadingDialog
import com.octopus.socialnetwork.ui.composable.backgroundTextShadow
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.composable.post.*
import com.octopus.socialnetwork.ui.composable.social_elements.interaction.InteractionGroup
import com.octopus.socialnetwork.ui.composable.social_elements.interaction.InteractionIcon
import com.octopus.socialnetwork.ui.screen.comments.navigateToCommentsScreen
import com.octopus.socialnetwork.ui.screen.post.state.PostMainUiState
import com.octopus.socialnetwork.ui.theme.LightBlack_65
import com.octopus.socialnetwork.ui.theme.White50
import com.octopus.socialnetwork.ui.util.Constants


@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PostContent(
        state = state,
        onClickBack = { navController.popBackStack() },
        onClickLike = viewModel::onClickLike,
        onComment = {
            navController.navigateToCommentsScreen(
                postId = state.postDetails.postId,
                type = Constants.COMMENT_TYPE
            )
        },
        onShare = { },
        onClickDelete = viewModel::onClickDelete,
        onClickTryAgain = viewModel::onClickTryAgain,
        changeDeletionDialogVisibility = viewModel::changeDeletionDialogVisibility,
        changeAgreeDeletionState = viewModel::changeAgreeDeletionState,
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PostContent(
    state: PostMainUiState,
    onClickBack: () -> Unit,
    onClickLike: () -> Unit,
    onComment: () -> Unit,
    onShare: () -> Unit,
    onClickDelete: (Int) -> Unit,
    onClickTryAgain: () -> Unit,
    changeDeletionDialogVisibility: () -> Unit,
    changeAgreeDeletionState: () -> Unit,
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

        if (state.isMyPost) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .clip(CircleShape)
                    .background(color = LightBlack_65)
                    .zIndex(1f)
                    .size(32.dp)
                    .align(alignment = Alignment.TopEnd)
            ) {
                IconButton(
                    onClick = changeDeletionDialogVisibility,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = stringResource(id = R.string.icon_arrow_back),
                        tint = White50,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }


        if (state.isLoading) {
            LottieLoading()
        } else if (state.isError) {
            LottieError(onClickTryAgain)
        } else {
            PostImage(postImage = state.postDetails.postImage)
        }

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
                    InteractionLikeIcon(state.postDetails, onClickLike)
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
                profileImage = state.postDetails.profileAvatar,
                userName = state.postDetails.userName,
                fullName = state.postDetails.fullName,
                postDescription = state.postDetails.postDescription,
            )
        }

    }

    if (state.isLoading) { LoadingDialog() }

    AnimatedVisibility(
        visible = state.isDeletionDialogVisible,
        enter = scaleIn(animationSpec = tween(200)),
        exit = scaleOut(animationSpec = tween(200)),
    ) {
        Dialog(
            onDismissRequest = changeDeletionDialogVisibility,
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            DeletionDialog(
                title = stringResource(R.string.delete_post),
                description = stringResource(R.string.confirm_delete_post),
                iconId = R.drawable.ic_crying_cat_face,
                onClickPrimaryAction = {
                    changeDeletionDialogVisibility()
                    onClickDelete(state.postDetails.postId)
                    changeAgreeDeletionState()
                },
                onClickCancel = changeDeletionDialogVisibility
            )
        }
    }

    LaunchedEffect(key1 = state.isAgreeDeletion) {
        if (state.isAgreeDeletion) {
            onClickBack()
        }
    }
}