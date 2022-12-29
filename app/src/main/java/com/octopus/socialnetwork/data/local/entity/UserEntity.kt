package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.utils.Constants

@Entity(tableName = Constants.PROFILE_TABLE)
data class UserEntity (
    @PrimaryKey(autoGenerate = false) val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val username: String,
    val email: String,
    val birthDate: String,
    val gender: String,
    val avatar: String,
    val coverUrl: String,
    val language: String,
)