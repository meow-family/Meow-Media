package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserEditDTO
import com.octopus.socialnetwork.domain.model.user.UserEdit

fun UserEditDTO.toUserEdit(): UserEdit {
    return UserEdit(
        userId = userId ?: 0,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        userName = userName ?: "",
        fullName = fullName ?: "",
        birthDate = birthDate ?: "",
        email = email ?: "",
    )
}