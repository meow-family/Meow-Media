package com.octopus.socialnetwork.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.octopus.socialnetwork.data.local.Converter
import com.octopus.socialnetwork.data.local.user.UserDao
import com.octopus.socialnetwork.data.local.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ], version = 1
)
@TypeConverters(Converter::class)
abstract class SocialDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}