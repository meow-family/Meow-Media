package com.octopus.socialnetwork.ui.screen.post.uistate

import java.util.*

data class PostUiState(
    val postId: Int = 0,
    val ownerId: Int = 0,
    val userName: String = "",
    val fullName: String = "",
    val postDate: Date = Date(),
    val profileAvatar: String = "",
    val postImage: String = "",
    val postDescription: String = "",
    val likeCount: String = "",
    val commentCount: String = "",
)