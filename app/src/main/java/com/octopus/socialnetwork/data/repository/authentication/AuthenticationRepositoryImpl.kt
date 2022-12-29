package com.octopus.socialnetwork.data.repository.authentication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.LoginDto
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFirebaseDTO
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import com.octopus.socialnetwork.data.utils.Constants.FCM_TOKEN
import com.octopus.socialnetwork.data.utils.Constants.NO_SUCH_ID
import com.octopus.socialnetwork.data.utils.Constants.REQUEST_SUCCEED
import com.octopus.socialnetwork.data.utils.Constants.TOKEN
import com.octopus.socialnetwork.data.utils.Constants.USERS_COLLECTION
import com.octopus.socialnetwork.data.utils.Constants.USER_ID_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val dataStore: DataStore<Preferences>,
    private val fireStore: FirebaseFirestore,
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): LoginDto {
        val response = service.login(username, password)
        if (response.code == REQUEST_SUCCEED) {
            dataStore.edit { MutableStringPref ->
                MutableStringPref[intPreferencesKey(USER_ID_KEY)] = response.result.id ?: -1
            }
        }
        return response.result
    }

    override suspend fun register(paramRegister: ParamRegisterDto): BaseResponse<RegisterDto> {
        return service.register(
            firstName = paramRegister.firstName,
            lastName = paramRegister.lastName,
            email = paramRegister.email,
            reEmail = paramRegister.reEmail,
            gender = paramRegister.gender,
            birthDate = paramRegister.birthDate,
            userName = paramRegister.userName,
            password = paramRegister.password
        )
    }

    override fun getUserId(): Flow<Int?> {
        return flow {
            dataStore.data.collect {
                emit(it[intPreferencesKey(USER_ID_KEY)])
            }
        }
    }

    override suspend fun getLocalFcmToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(FCM_TOKEN)]
    }

    override suspend fun writeFcmToken(token: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(FCM_TOKEN)] = token
        }
    }

    override suspend fun deleteUserId() {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[intPreferencesKey(USER_ID_KEY)] = NO_SUCH_ID
        }
    }

    override suspend fun createUser(user: UserFirebaseDTO) {
        fireStore.collection(USERS_COLLECTION).document(user.userId.toString())
            .set(user).await()
    }

    override suspend fun updateUser(user: UserFirebaseDTO) {
        fireStore.collection(USERS_COLLECTION).document(user.userId.toString())
            .set(user).await()
    }

    override suspend fun updateUserToken(userId: String, token: String) {
        fireStore.collection(USERS_COLLECTION).document(userId).update(TOKEN, token)
            .await()
    }

    override suspend fun getFirebaseUser(userId: String): UserFirebaseDTO? {
        return fireStore.collection(USERS_COLLECTION).document(userId).get().await()
            .toObject(UserFirebaseDTO::class.java)
    }

    override suspend fun getFirebaseFcmToken(): String? {
        return FirebaseMessaging.getInstance().token.await()
    }


    companion object {

    }
}