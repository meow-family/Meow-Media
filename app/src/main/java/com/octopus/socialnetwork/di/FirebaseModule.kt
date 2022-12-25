package com.octopus.socialnetwork.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.octopus.socialnetwork.data.remote.firebase.FirestoreService
import com.octopus.socialnetwork.data.remote.firebase.FirestoreServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseService(firebaseFireStore: FirebaseFirestore): FirestoreService {
        return FirestoreServiceImpl(firebaseFireStore)
    }


    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }
}