package com.octopus.socialnetwork.domain.model.comment

import java.util.*

data class Comment (
    val comment: String,
    val commentOwnerId: Int,
    val isLikedByUser: Boolean,
    val timeCreated: Date,
    val totalLikes: Int,
    val fullName: String,
    val username: String,
    val avatar: String,
    val commentId: Int,
)