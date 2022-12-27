package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.utils.Constants

@Entity(tableName = Constants.PROFILE_TABLE)
data class ProfileEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val fullName: String,
    val username: String,
    val avatar: String,
    val coverUrl: String,
)