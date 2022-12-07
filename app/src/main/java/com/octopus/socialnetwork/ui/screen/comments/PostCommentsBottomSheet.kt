package com.octopus.socialnetwork.ui.screen.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.comment.ItemComment
import com.octopus.socialnetwork.ui.composable.comment.TopBar
import com.octopus.socialnetwork.ui.composable.comment.TypingComment
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import kotlin.reflect.KSuspendFunction0


@Composable
fun PostCommentsBottomSheet(
    viewModel: CommentsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    PostCommentsContent(
        state = state,
        onChangeTypingComment = viewModel::onChangeTypingComment,
        onClickSend = viewModel::addComment
    )
}

 @Composable
private fun PostCommentsContent(
     state: CommentsUiState,
     onChangeTypingComment: (String) -> Unit,
     onClickSend: KSuspendFunction0<Unit>
) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
            ),
        verticalArrangement = Arrangement.SpaceEvenly
        ) {
        TopBar(title = stringResource(id = R.string.Comments))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(.8f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = listState
        ) {

            itemsIndexed(state.comments) { index, item ->
                ItemComment(commentDetails = item)
                if (index < state.comments.lastIndex)
                    Divider()
            }
        }

        TypingComment(
            state = state.textFieldCommentState,
            onChangeTypingComment = onChangeTypingComment,
            onClickSend = onClickSend,
            listState = listState,
            index = state.comments.lastIndex,
        )

    }

}

@Preview
@Composable
fun RegisterScreenPreview() {
    SocialNetworkTheme {
        Surface {
            PostCommentsBottomSheet()
        }
    }
}