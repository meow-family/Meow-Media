package com.octopus.socialnetwork.di

import com.octopus.socialnetwork.data.repository.sign_in.SignInRepository
import com.octopus.socialnetwork.data.repository.sign_in.SignInRepositoryImpl
import com.octopus.socialnetwork.data.repository.sign_up.SignUpRepository
import com.octopus.socialnetwork.data.repository.sign_up.SignUpRepositoryImpl
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