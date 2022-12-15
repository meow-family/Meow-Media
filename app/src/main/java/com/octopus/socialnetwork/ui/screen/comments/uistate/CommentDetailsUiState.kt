package com.octopus.socialnetwork.ui.screen.comments.uistate

import java.util.*

data class CommentDetailsUiState(
    val fullName: String = "",
    val userName: String = "",
    val userAvatar: String = "",
    val comment: String = "",
    val commentOwnerId : Int = 0,
    val isLikedByUser : Boolean = false,
    val likeCounter: Int = 0,
    val timeCreated: Date = Date(),
    val commentId: Int = 0,
)