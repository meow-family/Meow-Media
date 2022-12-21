package com.octopus.socialnetwork.di

import android.content.Context
import com.octopus.socialnetwork.domain.utils.ImageConverter
import com.octopus.socialnetwork.domain.utils.ImageConverterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MlKitModule {
    @Provides
    fun provideImageConverter(@ApplicationContext context: Context): ImageConverter {
        return ImageConverterImpl(context)
    }
}