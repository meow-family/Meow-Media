package com.octopus.socialnetwork.data.remote.response.dto.group.others

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("description")
    val description: String,
    @SerializedName("guid")
    val currentUserId: Int,
    @SerializedName("ismember")
    val isMember: Boolean,
    @SerializedName("membership")
    val membership: String,
    @SerializedName("owner_guid")
    val ownerGuid: Int,
    @SerializedName("request_exists")
    val requestExists: Boolean,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("time_created")
    val timeCreated: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_requests")
    val totalRequests: Int,
    @SerializedName("type")
    val type: String
)