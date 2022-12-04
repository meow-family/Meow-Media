package com.octopus.socialnetwork.domain.model.user


data class UserDetails(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val username: String,
    val email: String,
    val birthDate: String,
    val gender: String,
    val avatar: String,
    val coverUrl: String,
    val language: String,
)
