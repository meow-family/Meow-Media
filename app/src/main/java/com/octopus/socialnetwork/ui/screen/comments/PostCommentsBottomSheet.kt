package com.octopus.socialnetwork.ui.screen.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.ui.composable.comment.TypingComment
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentsUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme


@Composable
fun PostCommentsBottomSheet(
    viewModel: CommentsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    PostCommentsContent(
        state = state,
        onChangeTypingComment = viewModel::onChangeTypingComment
    )
}

@Composable
private fun PostCommentsContent(
    state: CommentsUiState,
    onChangeTypingComment: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = AbsoluteRoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )

    ) {

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(.8f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.comments) { index, item ->
                ItemComment(commentDetails = item)
                if (index < state.comments.lastIndex)
                    Divider()
            }
        }

        TypingComment(
            state = state.textFieldCommentState,
            onChangeTypingComment = onChangeTypingComment
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
