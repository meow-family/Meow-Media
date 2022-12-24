package com.octopus.socialnetwork.domain.model.user


data class UserFirebase(
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val token: String?
)
