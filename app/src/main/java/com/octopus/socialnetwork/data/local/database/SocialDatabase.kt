package com.octopus.socialnetwork.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.socialnetwork.data.local.dao.ConversationDao
import com.octopus.socialnetwork.data.local.dao.PostsDao
import com.octopus.socialnetwork.data.local.dao.RemoteKeysDao
import com.octopus.socialnetwork.data.local.dao.UserDao
import com.octopus.socialnetwork.data.local.entity.ConversationEntity
import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.local.entity.RemoteKeyEntity
import com.octopus.socialnetwork.data.local.entity.UserEntity

@Database(
    entities = [
        PostEntity::class,
        RemoteKeyEntity::class,
        UserEntity::class,
        ConversationEntity::class,
    ], version = 1
)
abstract class SocialDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun userDao(): UserDao
    abstract fun conversationsDao() : ConversationDao
}