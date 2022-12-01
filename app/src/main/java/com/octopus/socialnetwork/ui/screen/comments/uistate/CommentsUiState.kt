package com.octopus.socialnetwork.ui.screen.comments.uistate

import androidx.compose.ui.text.input.TextFieldValue

data class CommentsUiState(
    val comments: List<CommentDetailsUiState> = emptyList(),
    var textFieldCommentState: TextFieldValue = TextFieldValue()
)
