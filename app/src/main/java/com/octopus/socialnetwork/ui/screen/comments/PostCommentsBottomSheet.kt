package com.octopus.socialnetwork.ui.screen.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.ui.composable.comment.ItemComment
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PostCommentsContent(
    state: CommentsUiState,
    onChangeTypingComment: (String) -> Unit,
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =
        BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(
                        color = Color.White,
                        shape = AbsoluteRoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
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
        },
        sheetPeekHeight = 0.dp
    ) {}

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
