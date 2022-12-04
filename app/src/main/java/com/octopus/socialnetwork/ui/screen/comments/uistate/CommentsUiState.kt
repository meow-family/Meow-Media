package com.octopus.socialnetwork.ui.screen.comments.uistate

import androidx.compose.ui.text.input.TextFieldValue

data class CommentsUiState(
    val comments: List<CommentDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    var textFieldCommentState: TextFieldValue = TextFieldValue()
)
