package com.octopus.socialnetwork.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.socialnetwork.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: List<RemoteKeyEntity>)

    @Query("SELECT * FROM remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeyById(id: Int): RemoteKeyEntity

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}