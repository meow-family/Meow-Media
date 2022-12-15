package com.octopus.socialnetwork.domain.model.comment

data class Comment (
    val comment: String,
    val commentOwnerId: Int,
    val isLikedByUser: Boolean,
    val timeCreated: Int,
    val totalLikes: Int,
    val fullName: String,
    val username: String,
    val avatar: String,
    val commentId: Int,
)