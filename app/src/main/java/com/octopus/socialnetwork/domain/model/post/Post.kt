package com.octopus.socialnetwork.domain.model.post

data class Post(
    val guid: Int,
    val ownerGuid: Int,
    val title: String,
)
