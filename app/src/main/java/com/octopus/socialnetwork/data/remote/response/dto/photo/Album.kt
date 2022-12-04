package com.octopus.socialnetwork.data.remote.response.dto.photo


import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("access")
    val access: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("description")
    val description: String,
    @SerializedName("guid")
    val guid: Int,
    @SerializedName("item_guid")
    val itemGuid: String,
    @SerializedName("item_type")
    val itemType: String,
    @SerializedName("owner_guid")
    val ownerGuid: Int,
    @SerializedName("poster_guid")
    val posterGuid: String,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("time_created")
    val timeCreated: Int,
    @SerializedName("time_updated")
    val timeUpdated: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
) {
    class Data
}