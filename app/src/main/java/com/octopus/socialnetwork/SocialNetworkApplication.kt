package com.octopus.socialnetwork

import android.app.Application
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SocialNetworkApplication : Application() {

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    override fun onCreate() {
        super.onCreate()
        checkFirstTimeLaunch()
    }

    private fun checkFirstTimeLaunch() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStorePreferences.readString(USER_ID_KEY).let { id ->
                isLoggedOut =  id == NO_SUCH_ID || id == null
            }
        }
    }

    companion object {
        var isLoggedOut = false
        const val USER_ID_KEY = "user_id"
        const val NO_SUCH_ID = -1
    }
}