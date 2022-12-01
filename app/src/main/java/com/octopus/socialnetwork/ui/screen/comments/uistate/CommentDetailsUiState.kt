package com.octopus.socialnetwork.ui.screen.comments.uistate

data class CommentDetailsUiState(
    val fullName: String = "",
    val userName: String = "",
    val userAvatar: String = "",
    val comment: String = "",
    val likeCounter: String = "",
    val timeAgo: String = ""
)