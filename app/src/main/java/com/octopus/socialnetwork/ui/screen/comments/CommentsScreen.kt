package com.octopus.socialnetwork.ui.screen.comments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.ManualPager
import com.octopus.socialnetwork.ui.composable.comment.ItemComment
import com.octopus.socialnetwork.ui.composable.comment.TypingField
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.screen.comments.state.CommentsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


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
        onClickTryAgain = viewModel::onClickTryAgain,
        onRefresh = viewModel::swipeToRefresh
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
private fun CommentsContent(
    state: CommentsUiState,
    onChangeTypingComment: (String) -> Unit,
    onClickSend: () -> Unit,
    onClickBack: () -> Unit,
    onClickLike: (Int, Int, Boolean) -> Unit,
    onClickTryAgain: () -> Unit,
    onRefresh: () -> Unit,
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
        }

        if (state.isError) {
            LottieError(onClickTryAgain)
        }
        if (state.comments.isEmpty()) {
            ImageForEmptyList(modifier = Modifier.padding(vertical = 64.dp))
        }



        ManualPager(
            onRefresh = onRefresh,
            contentPadding = PaddingValues(vertical = 16.dp),
            isLoading = state.isPagerLoading,
            error = state.pagerError,
            isEndOfPager = state.isEndOfPager,
            modifier = Modifier.fillMaxWidth().weight(.8f),
        ) {


            itemsIndexed(state.comments) { index, comment ->
                if (state.error.isEmpty()) {
                    ItemComment(state = comment, onLike = onClickLike)
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



//    LaunchedEffect(key1 = state.isSent) {
//        if (state.isSent)
//            listState.animateScrollToItem(index = state.comments.lastIndexOrZero())
//    }

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