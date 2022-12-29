package com.octopus.socialnetwork.data.local.dao

import androidx.room.*
import com.octopus.socialnetwork.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileDetails(profileInfo: UserEntity)

    @Query("SELECT * FROM profile_table")
     fun getProfileDetails(): Flow<UserEntity>
}