package com.octopus.socialnetwork.ui.screen.comments

data class CommentDetailsUiState(
    val fullName: String = "",
    val userName: String = "",
    val userProfileImage:String = "",
    val comment:String = "",
    val likeCounter:String  = "",
    val timeAgo: String = ""
)