package com.octopus.socialnetwork.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.remote.response.dto.Avatar

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Long,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val username: String,
//    val avatar: Avatar,
    val coverUrl: String,
)

