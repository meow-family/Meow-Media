package com.octopus.socialnetwork.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octopus.socialnetwork.data.utils.Constants

@Entity(tableName = Constants.REMOTE_KEYS_TABLE)
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val previousPage: Int?,
    val nextPage: Int?,
)
