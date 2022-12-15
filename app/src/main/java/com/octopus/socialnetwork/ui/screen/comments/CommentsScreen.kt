package com.octopus.socialnetwork.ui.screen.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
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
import com.octopus.socialnetwork.ui.composable.comment.ItemComment
import com.octopus.socialnetwork.ui.composable.comment.TypingComment
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.util.extensions.lastIndexOrZero
import kotlin.reflect.KSuspendFunction0


@Composable
fun CommentsScreen(
    navController: NavController,
    viewModel: CommentsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CommentsContent(
        state = state,
        onChangeTypingComment = viewModel::onChangeTypingComment,
        onClickSend = viewModel::addComment,
        onClickBack = { navController.popBackStack() },
        onClickLike = viewModel::onClickLike
    )
}

@Composable
private fun CommentsContent(
    state: CommentsUiState,
    onChangeTypingComment: (String) -> Unit,
    onClickSend: KSuspendFunction0<Unit>,
    onClickBack: () -> Unit,
    onClickLike: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AppBar(onClickBack, title = stringResource(id = R.string.Comments))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(.8f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = listState
        ) {

            itemsIndexed(state.comments) { index, item ->
                ItemComment(commentDetails = item, onLike = { onClickLike(item.commentId) })
                if (index < state.comments.lastIndex)
                    Divider()
            }
        }

        TypingComment(
            value = state.comment,
            onChangeTypingComment = onChangeTypingComment,
            onClickSend = onClickSend,
            listState = listState,
            index = state.comments.lastIndexOrZero(),
        )

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