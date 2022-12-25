package com.octopus.socialnetwork.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.socialnetwork.data.local.dao.CommentsDao
import com.octopus.socialnetwork.data.local.dao.MessagesDao
import com.octopus.socialnetwork.data.local.dao.PostsDao
import com.octopus.socialnetwork.data.local.dao.RemoteKeysDao
import com.octopus.socialnetwork.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        PostEntity::class,
        CommentEntity::class,
        MessageEntity::class,
        RemoteKeyEntity::class,
    ], version = 2
)
abstract class SocialDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun commentDao(): CommentsDao
    abstract fun messageDao(): MessagesDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}