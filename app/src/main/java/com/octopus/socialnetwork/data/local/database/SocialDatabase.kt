package com.octopus.socialnetwork.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octopus.socialnetwork.data.local.user.UserDao
import com.octopus.socialnetwork.data.local.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ], version = 1
)
abstract class SocialDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}