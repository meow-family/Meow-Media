package com.octopus.socialnetwork.data.local.dao

import androidx.room.*
import com.octopus.socialnetwork.data.local.entity.ProfileEntity

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileDetails(profileInfo: ProfileEntity)

    @Query("SELECT * FROM profile_table")
    fun getProfileDetails(): ProfileEntity

    @Delete
    suspend fun deleteProfileDetails(profileEntity: ProfileEntity)

    @Query("UPDATE profile_table SET fullName = :fullName, username = :username WHERE id = :id")
    suspend fun updateUserInfo(
        id: Int,
        fullName: String,
        username: String,
    )

    @Query("UPDATE profile_table SET avatar = :avatar WHERE id = :id")
    suspend fun updatePersonalPhoto(
        id: Int,
        avatar: String,
    )

    @Query("UPDATE profile_table SET coverUrl = :coverUrl WHERE id = :id")
    suspend fun updateCoverPhoto(
        id: Int,
        coverUrl: String,
    )
}