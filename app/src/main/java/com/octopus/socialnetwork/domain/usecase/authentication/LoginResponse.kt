package com.octopus.socialnetwork.domain.usecase.authentication

sealed class LoginResponse {
    object Success : LoginResponse()
    data class Failure(val message: String, val code: String) : LoginResponse()
}