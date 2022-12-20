package com.octopus.socialnetwork.data.local.mappers.user

import com.octopus.socialnetwork.data.local.user.UserEntity
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun map(user: UserDto): UserEntity {
        return UserEntity(
            id = user.id?.toLong() ?: 0,
            firstName = user.firstName ?: "",
            lastName = user.lastName ?: "",
            username = user.username ?: "",
            fullName = user.fullName ?: "",
//            avatar = user.avatar.larger ?: "",
            coverUrl = user.coverUrl ?: "",
        )
    }


}