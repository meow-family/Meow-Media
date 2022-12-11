package com.octopus.socialnetwork.domain.model.user


data class UserEdit(
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val fullName: String,
    val birthDate: String,
//    val coverUrl: String,
    val email: String,
//    val icon: String
)