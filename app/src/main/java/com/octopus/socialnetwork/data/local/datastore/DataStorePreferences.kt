package com.octopus.socialnetwork.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePreferences {
    fun readBoolean(key: String): Flow<Boolean?>
    suspend fun writeBoolean(key: String, value: Boolean)
    fun readInt(key: String): Flow<Int?>
    suspend fun writeInt(key: String, value: Int)
    suspend fun readString(key: String): String?
    suspend fun writeString(key: String, value: String)
}