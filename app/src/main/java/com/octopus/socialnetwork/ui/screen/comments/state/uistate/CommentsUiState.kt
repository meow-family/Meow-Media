package com.octopus.socialnetwork.ui.screen.comments.state.uistate

data class CommentsUiState(
    val comments: List<CommentDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    var comment: String = "",
    val isSent: Boolean = false,
)
