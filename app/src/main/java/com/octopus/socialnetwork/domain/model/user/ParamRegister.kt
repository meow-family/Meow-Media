package com.octopus.socialnetwork.domain.model.user

data class ParamRegister(
    val firstName: String,
    val lastName: String,
    val email: String,
    val reEmail: String,
    val gender: String,
    val birthDate: String,
    val userName: String,
    val password: String
)