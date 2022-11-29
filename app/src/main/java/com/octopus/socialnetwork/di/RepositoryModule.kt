package com.octopus.socialnetwork.di

import com.octopus.socialnetwork.data.repository.authentication_repository.SignInRepository
import com.octopus.socialnetwork.data.repository.authentication_repository.SignInRepositoryImpl
import com.octopus.socialnetwork.data.repository.authentication_repository.SignUpRepository
import com.octopus.socialnetwork.data.repository.authentication_repository.SignUpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindSignInRepository(
        signInRepositoryImpl: SignInRepositoryImpl,
    ): SignInRepository

    @ViewModelScoped
    @Binds
    abstract fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl,
    ): SignUpRepository

}