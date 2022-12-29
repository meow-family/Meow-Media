package com.octopus.socialnetwork.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.socialnetwork.data.local.entity.MessageEntity


@Dao
interface ConversationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversations(conversations: List<MessageEntity>)

    @Query("SELECT * FROM message_table ORDER BY id DESC")
    fun getAllConversations(): List<MessageEntity>
}