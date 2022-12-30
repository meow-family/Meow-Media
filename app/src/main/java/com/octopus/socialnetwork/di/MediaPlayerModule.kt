package com.octopus.socialnetwork.di

import android.content.Context
import android.media.MediaPlayer
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.domain.utils.SoundPlayer
import com.octopus.socialnetwork.domain.utils.SoundPlayerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MediaPlayerModule {
    @Provides
    fun provideMediaPlayer(@ApplicationContext context: Context): MediaPlayer {
        return MediaPlayer.create(context, R.raw.meow_sound)
    }

    @Provides
    fun provideSoundPlayer(mediaPlayer: MediaPlayer): SoundPlayer {
        return SoundPlayerImpl(mediaPlayer)
    }

}