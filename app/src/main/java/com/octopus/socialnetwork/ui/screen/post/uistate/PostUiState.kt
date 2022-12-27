package com.octopus.socialnetwork.ui.screen.post.uistate


data class PostUiState(
    val postId: Int = 0,
    val ownerId: Int = 0,
    val userName: String = "",
    val fullName: String = "",
    val postDate: String = "",
    val profileAvatar: String = "",
    val postImage: String = "",
    val postDescription: String = "",
    val likeCount: String = "",
    val commentCount: String = "",
    val isLiked: Boolean = false

)

