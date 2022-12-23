package com.octopus.socialnetwork.domain.model.post

import java.util.*


data class Post(
    val postId: Int,
    val ownerId: Int,
    var description: String,
    val image: String,
    val fullName: String,
    val username: String,
    val avatar: String,
    val totalLikes: Int,
    val totalComments: Int,
    val isLikedByUser: Boolean,
    val timeCreated: Date,
)
