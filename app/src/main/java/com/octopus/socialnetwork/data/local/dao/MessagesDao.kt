package com.octopus.socialnetwork.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.socialnetwork.data.local.entity.MessageEntity

interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)
//
//    @Query("SELECT * FROM message_table")
//    fun getAllPosts(): PagingSource<Int, MessageEntity>

    @Query("DELETE FROM message_table")
    suspend fun deleteAllPosts()

}