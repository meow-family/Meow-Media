package com.octopus.socialnetwork.di

import android.content.Context
import androidx.room.Room
import com.octopus.socialnetwork.data.local.dao.UserDao
import com.octopus.socialnetwork.data.local.database.SocialDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SocialDatabase {
        return Room.databaseBuilder(
            context,
            SocialDatabase::class.java,
            "SOCIAL_DATABASE"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: SocialDatabase): UserDao {
        return database.userDao()
    }
}