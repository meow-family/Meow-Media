package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.utils.Constants

@Entity(tableName = Constants.MESSAGE_TABLE)
data class MessageEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val answered: Int,
    val message: String,
    val userId: Int,
    val fullName: String,
    val avatar: String,
    val username: String,
    val time: Long,
    val viewed: String,
)
