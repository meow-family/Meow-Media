package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.utils.Constants
import java.util.*

@Entity(tableName = Constants.COMMENT_TABLE)
data class CommentEntity(
    @PrimaryKey(autoGenerate = false)
    val commentId: Int ,
    val fullName: String,
    val userName: String ,
    val userAvatar: String ,
    val comment: String ,
    val commentOwnerId : Int,
    val isLikedByUser : Boolean ,
    val timeCreated: Long,
    val totalLikes: Int,
    val postId: Int
)
