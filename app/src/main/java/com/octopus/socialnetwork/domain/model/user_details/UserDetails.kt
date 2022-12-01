package com.octopus.socialnetwork.domain.model.user_details


data class UserDetails(
    val guid: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val username: String,
    val email: String,
    val birthDate: String,
    val gender: String,
    val icon: String,
    val coverUrl: String,
    val language: String,
)
