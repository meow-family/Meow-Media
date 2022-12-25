package com.octopus.socialnetwork.domain.model.user

data class UserEdit(
    val guid: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val fullName: String,
    val birthdate: String,
    val coverUrl: String,
    val email: String,
    val icon: String
)