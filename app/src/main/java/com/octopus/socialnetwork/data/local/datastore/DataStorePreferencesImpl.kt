package com.octopus.socialnetwork.data.local.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.octopus.socialnetwork.data.utils.Constants.FCM_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStorePreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStorePreferences {
    override fun readBoolean(key: String): Flow<Boolean?> {
        return dataStore.data.map { preference ->
            preference[booleanPreferencesKey(key)]
        }
    }

    override suspend fun writeBoolean(key: String, value: Boolean) {
        dataStore.edit { MutableBooleanPref ->
            MutableBooleanPref[booleanPreferencesKey(key)] = value
        }
    }

    override fun readString(key: String): Int? {
        return runBlocking {
            dataStore.data.map { preference ->
                preference[intPreferencesKey(key)]
            }.first()
        }
    }

    override suspend fun writeString(key: String, value: Int) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[intPreferencesKey(key)] = value
        }
    }

    override suspend fun writeFcmToken(key: String, value: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun readFcmToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(FCM_TOKEN)]
    }

}