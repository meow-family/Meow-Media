package com.octopus.socialnetwork.di

import com.octopus.socialnetwork.BuildConfig
import com.octopus.socialnetwork.data.remote.interceptor.AuthInterceptor
import com.octopus.socialnetwork.data.remote.service.fcm.CloudMessagingService
import com.octopus.socialnetwork.data.remote.service.service.SocialService
import com.simplemented.okdelay.DelayInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(DelayInterceptor(2000L,TimeUnit.MILLISECONDS))
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideFirebaseCloudMessagingService(retrofit: Retrofit): CloudMessagingService =
            retrofit.create(CloudMessagingService::class.java)

    @Provides
    @Singleton
    fun provideSocialService(retrofit: Retrofit): SocialService =
        retrofit.create(SocialService::class.java)


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


}
