package com.octopus.socialnetwork.domain.model.post

data class Post(
    val postId: Int,
    val ownerId: Int,
    val description: String,
    val image: String,
    val fullName: String,
    val username: String,
    val avatar: String,
    val totalLikes: Int,
    val totalComments: Int,
    val isLikedByUser: Boolean
)
