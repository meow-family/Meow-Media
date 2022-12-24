package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.domain.model.user.UserFirebase


fun UserFirebaseDTO.toUserFirebase(): UserFirebase {
    return UserFirebase(
        userId = userId ?: 0,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        username = username ?: "",
        token = token ?: "",
    )

}