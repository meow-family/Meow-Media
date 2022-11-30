package com.octopus.socialnetwork.ui.screen.post

data class PostUiState(
    val likeCount: String = "",
    val commentCount: String = "",
    val userName: String = "",
    val fullName: String = "",
    val profileImage: String = "",
    val postImage: String = "",
    val postDate: String = "",
    val postDescription: String = "",
)
