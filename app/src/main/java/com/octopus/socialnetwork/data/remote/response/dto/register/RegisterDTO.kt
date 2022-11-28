package com.octopus.socialnetwork.data.remote.response.dto.register

data class RegisterDTO(
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val reEmail: String,
    val password: String,
    val gender: String,
    val birthdate: String,
)