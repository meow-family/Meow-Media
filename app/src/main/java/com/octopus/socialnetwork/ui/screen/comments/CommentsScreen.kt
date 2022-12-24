package com.octopus.socialnetwork.ui.screen.comments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.comment.ItemComment
import com.octopus.socialnetwork.ui.composable.comment.TypingField
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.util.extensions.lastIndexOrZero


@Composable
fun CommentsScreen(
    navController: NavController,
    viewModel: CommentsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CommentsContent(
        state = state,
        onChangeTypingComment = viewModel::onChangeTypingComment,
        onClickSend = viewModel::onClickSend,
        onClickBack = { navController.popBackStack() },
        onClickLike = viewModel::onClickLike,
        onClickTryAgain = viewModel::onClickTryAgain
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
private fun CommentsContent(
    state: CommentsUiState,
    onChangeTypingComment: (String) -> Unit,
    onClickSend: () -> Unit,
    onClickBack: () -> Unit,
    onClickLike: (Int) -> Unit,
    onClickTryAgain: () -> Unit
) {
    val listState = rememberLazyListState()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            AppBar(onClickBack, title = stringResource(id = R.string.Comments))
            if (state.isLoading) {
                LottieLoading()
            } else if (state.comments.isEmpty()) {
                ImageForEmptyList(modifier = Modifier.fillMaxWidth().weight(1f))
            }else if (state.isError ){
                LottieError(onClickTryAgain)
            } else {

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(.8f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = listState,
                reverseLayout = true,
            ) {

                    itemsIndexed(state.comments) { index, item ->
                        ItemComment(
                            commentDetails = item,
                            onLike = { onClickLike(item.commentId) }
                        )
                        if (index < state.comments.lastIndex) Divider()
                    }
                }
            }

            TypingField(
                onChangeTypingComment = onChangeTypingComment,
                onClickSend = onClickSend,
                state = state
            )

        }



    LaunchedEffect(key1 = state.isSent ){
       if (state.isSent)
           listState.animateScrollToItem(index = state.comments.lastIndexOrZero())
    }

}


@Preview
@Composable
fun CommentsScreenPreview() {
    SocialNetworkTheme {
        Surface {
            //CommentsScreen()
        }
    }
}