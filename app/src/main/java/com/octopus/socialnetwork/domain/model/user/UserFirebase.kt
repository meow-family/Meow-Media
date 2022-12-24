package com.octopus.socialnetwork.domain.model.user


data class UserFirebase(
    val userId: Int,
    val fullName: String,
    val username: String,
    val token: String?
)
