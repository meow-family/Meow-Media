package com.octopus.socialnetwork.data.remote.service

import com.google.firebase.firestore.FirebaseFirestore
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.utils.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseServiceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : FirebaseService {

    override suspend fun createUser(user: UserFirebaseDTO) {
        fireStore.collection(Constants.USERS_COLLECTION).document(user.userId.toString())
            .set(user).await()

    }

    override suspend fun updateUser(user: UserFirebaseDTO) {
        fireStore.collection(Constants.USERS_COLLECTION).document(user.userId.toString())
            .set(user).await()

    }

    override suspend fun getUser(userId: String): UserFirebaseDTO? {
        return fireStore.collection(Constants.USERS_COLLECTION).document(userId).get().await()
            .toObject(UserFirebaseDTO::class.java)
    }
}

