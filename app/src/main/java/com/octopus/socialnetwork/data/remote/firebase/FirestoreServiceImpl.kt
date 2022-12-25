package com.octopus.socialnetwork.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.utils.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreServiceImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : FirestoreService {

    override suspend fun createUser(user: UserFirebaseDTO) {
        fireStore.collection(Constants.USERS_COLLECTION).document(user.userId.toString())
            .set(user).await()

    }

    override suspend fun updateUser(user: UserFirebaseDTO) {
        fireStore.collection(Constants.USERS_COLLECTION).document(user.userId.toString())
            .set(user).await()

    }

    override suspend fun updateUserToken(userId: String, token: String) {
        fireStore.collection(Constants.USERS_COLLECTION).document(userId).update("token", token)
            .await()
    }

    override suspend fun getUser(userId: String): UserFirebaseDTO? {
        return fireStore.collection(Constants.USERS_COLLECTION).document(userId).get().await()
            .toObject(UserFirebaseDTO::class.java)
    }
}

