package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.utils.Constants

@Entity(tableName = Constants.POSTS_TABLE)
data class PostEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val ownerId: Int,
    val description: String,
    val imageUrl: String,
    val fullName: String,
    val username: String,
    val avatarUrl: String,
    val totalLikes: Int,
    val totalComments: Int,
    val isLikedByUser: Boolean,
    val timeCreated: Long,
)
