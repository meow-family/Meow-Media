package com.octopus.socialnetwork.data.remote.response.dto.user

data class UserFirebaseDTO(
    val userId: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val token: String = ""
)