package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.domain.model.user.UserFirebase


fun UserFirebaseDTO.toUserFirebase(): UserFirebase {
    return UserFirebase(
        userId = userId ?: 0,
        fullName = fullName ?: "",
        username = username ?: "",
        token = token ?: "",
    )

}

fun UserFirebase.toUserFirebaseDTO(): UserFirebaseDTO {
    return UserFirebaseDTO(
        userId = userId ?: 0,
        fullName = fullName ?: "",
        username = username ?: "",
        token = token ?: "",
    )

}