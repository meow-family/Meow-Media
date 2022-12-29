package com.octopus.socialnetwork.data.mapper

import com.octopus.socialnetwork.data.local.entity.UserEntity
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id ?: 0,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        fullName = fullName ?: "",
        username = username ?: "",
        email = email ?: "",
        birthDate = birthDate ?: "",
        gender = gender ?: "",
        avatar = avatar?.larger ?: "",
        coverUrl = coverUrl ?: "",
        language = language ?: "",
    )
}