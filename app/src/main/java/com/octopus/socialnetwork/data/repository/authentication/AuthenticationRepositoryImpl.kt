package com.octopus.socialnetwork.data.repository.authentication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.firebase.firestore.FirebaseFirestore
import com.octopus.socialnetwork.SocialNetworkApplication.Companion.USER_ID_KEY
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.remote.service.service.SocialService
import com.octopus.socialnetwork.data.utils.Constants
import com.octopus.socialnetwork.data.utils.Constants.FCM_TOKEN
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val dataStore: DataStore<Preferences>,
    private val fireStore: FirebaseFirestore,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): AuthResponse {
        val response = service.login(username, password)
        if (response.code == REQUEST_SUCCEED) {
            dataStore.edit { MutableStringPref ->
                MutableStringPref[intPreferencesKey(USER_ID_KEY)] = response.result.id ?: 0
            }
        }
        return response.result
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String
    ): BaseResponse<RegisterDto> {
        return service.register(
            firstName = firstName,
            lastName = lastName,
            email = email,
            reEmail = reEmail,
            gender = gender,
            birthDate = birthDate,
            userName = userName,
            password = password
        )
    }

    override suspend fun getUserId(): Int? {
        val preferences = dataStore.data.first()
        return preferences[intPreferencesKey("user_id")]
    }

    override suspend fun getLocalFcmToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey("FCM_TOKEN")]
    }

    override suspend fun writeFcmToken(token: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(FCM_TOKEN)] = token
        }
    }

    override suspend fun deleteUserId() {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[intPreferencesKey("user_id")] = NO_SUCH_ID
        }
    }

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

    override suspend fun getFirebaseUser(userId: String): UserFirebaseDTO? {
        return fireStore.collection(Constants.USERS_COLLECTION).document(userId).get().await()
            .toObject(UserFirebaseDTO::class.java)
    }

    companion object {
        const val REQUEST_SUCCEED = "100"
        const val NO_SUCH_ID = -1
    }
}