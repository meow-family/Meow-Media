package com.octopus.socialnetwork.di

import android.content.Context
import androidx.room.Room
import com.octopus.socialnetwork.data.local.dao.ConversationsDao
import com.octopus.socialnetwork.data.local.database.SocialDatabase
import com.octopus.socialnetwork.data.local.dao.PostsDao
import com.octopus.socialnetwork.data.local.dao.RemoteKeysDao
import com.octopus.socialnetwork.data.local.dao.UserDao
import com.octopus.socialnetwork.di.Constants.DATABASE_NAME
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
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providePostsDao(database: SocialDatabase): PostsDao {
        return database.postsDao()
    }

    @Singleton
    @Provides
    fun provideRemoteKeysDao(database: SocialDatabase): RemoteKeysDao {
        return database.remoteKeysDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: SocialDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideConversationsDao(database: SocialDatabase): ConversationsDao {
        return database.ConversationsDao()
    }
}