package com.octopus.socialnetwork.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.ExperimentalPagingApi
import com.google.firebase.firestore.FirebaseFirestore
import com.octopus.socialnetwork.data.remote.service.service.SocialService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepositoryImpl
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepositoryImpl
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.data.repository.social.SocialRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ExperimentalPagingApi
    @ViewModelScoped
    @Binds
    abstract fun bindSocialRepository(
        socialRepositoryImpl: SocialRepositoryImpl,
    ): SocialRepository

    @ViewModelScoped
    @Binds
    abstract fun bindMessagingRepository(
        messagingRepositoryImpl: MessagingRepositoryImpl,
    ): MessagingRepository


}