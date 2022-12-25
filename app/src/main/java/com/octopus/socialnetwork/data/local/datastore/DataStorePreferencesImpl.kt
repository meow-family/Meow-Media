package com.octopus.socialnetwork.data.local.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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

    override fun readInt(key: String): Flow<Int?> {
        return flow {
            val preferences = dataStore.data.first()
            emit(preferences[intPreferencesKey(key)])
        }
    }

    override suspend fun writeInt(key: String, value: Int) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[intPreferencesKey(key)] = value
        }
    }

    override suspend fun readString(key: String): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(key)]
    }

    override suspend fun writeString(key: String, value: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(key)] = value
        }
    }

}