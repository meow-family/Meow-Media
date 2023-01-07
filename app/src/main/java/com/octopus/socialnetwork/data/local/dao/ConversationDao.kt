package com.octopus.socialnetwork.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.socialnetwork.data.local.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversations(conversations: List<ConversationEntity>)

    @Query("SELECT * FROM CONVERSATION_TABLE ORDER BY time DESC")
    fun getAllConversations(): Flow<List<ConversationEntity>>

    @Query("UPDATE CONVERSATION_TABLE SET lastMessage = :lastMessage, time = :time WHERE friendId = :friendId")
    suspend fun updateConversation(friendId: Int, lastMessage: String, time: String)
}