package com.octopus.socialnetwork.ui.screen.post

data class PostUiState(
    val likeCount: String = "",
    val commentCount: String = "",
    val shareCount: String = "",
    val userName: String = "",
    val fullName: String = "",
    val profileImage: String = "",
    val postImage: String = "",
    val postDescription: String = "",
)
