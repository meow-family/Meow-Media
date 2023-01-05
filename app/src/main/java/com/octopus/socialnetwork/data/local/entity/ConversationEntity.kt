package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CONVERSATION_TABLE")
data class ConversationEntity(
    @PrimaryKey(autoGenerate = false) val friendId: Int,
    val friendName: String,
    val avatar: String,
    val lastMessage: String,
    val time: Long,
)