package com.octopus.socialnetwork

import android.app.Application
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SocialNetworkApplication : Application() {

    @Inject
    lateinit var authenticationRepository: AuthenticationRepository

    override fun onCreate() {
        super.onCreate()
        checkFirstTimeLaunch()
    }

    private fun checkFirstTimeLaunch() {
        CoroutineScope(Dispatchers.IO).launch {

            authenticationRepository.getUserId().let { id ->
                isLoggedOut = id == NO_SUCH_ID || id == null
            }

        }
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i("TESTING", it.result.toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        authenticationRepository.writeFcmToken(it.result.toString())
                    }
                }
            }.addOnFailureListener {
                Log.i("TESTING", it.toString())
            }


    }

    companion object {
        var isLoggedOut = false
        const val USER_ID_KEY = "user_id"
        const val NO_SUCH_ID = -1
    }
}