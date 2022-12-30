package com.octopus.socialnetwork.ui.screen.comments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.AppBar
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.comment.ItemComment
import com.octopus.socialnetwork.ui.composable.comment.TypingField
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.screen.comments.state.CommentsUiState
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
    onClickLike: (Int, Int, Boolean) -> Unit,
    onClickTryAgain: () -> Unit
) {
    val listState = rememberLazyListState()
    val comments = state.comments.collectAsLazyPagingItems()
    val isEmptyFlow = comments.itemSnapshotList.isEmpty()

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
        if (isEmptyFlow) {
            ImageForEmptyList(
                modifier = Modifier.weight(1f).fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }


        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(.8f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = listState,
            reverseLayout = true,
        ) {

            itemsIndexed(comments) { index, comment ->
                comment?.let {
                    ItemComment(state = it, onLike = onClickLike)
                }
                if (index < comments.itemSnapshotList.lastIndex) Divider()
            }
            when (comments.loadState.append) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    item {  LottieLoading() }
                }
                is LoadState.Error -> {
                    item { LottieLoading() }
                }
            }
        }

        TypingField(
            onChangeTypingComment = onChangeTypingComment,
            onClickSend = onClickSend,
            state = state
        )

    }



    LaunchedEffect(key1 = state.isSent) {
        if (state.isSent)
            listState.animateScrollToItem(index = comments.itemSnapshotList.lastIndexOrZero())
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