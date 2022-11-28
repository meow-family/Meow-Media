package com.octopus.socialnetwork.data.remote.response.dto

data class RegisterDTO(
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val reemail: String,
    val password: String,
    val gender: String,
    val birthdate: String,
)