package com.octopus.socialnetwork.ui.screen.post.uistate

data class PostUiState(
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val userName: String = "",
    val fullName: String = "",
    val profileAvatar: String = "",
    val postImage: String = "",
    val postDescription: String = "",
    val likeCount: String = "",
    val commentCount: String = "",
)