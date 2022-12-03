package com.octopus.socialnetwork.domain.model.post

data class Post(
    val postId: Int,
    val ownerId: Int,
    val description: String,
    val image: String,

)
