package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

data class AlbumDetailsDto(
    @SerializedName("guid")
    val id: Int?,
    @SerializedName("owner_guid")
    val ownerId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("access")
    val privacy: String?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("file:ossn:aphoto")
    val fileOSSNPhoto: String?,
    @SerializedName("data")
    val data: Data?,
) {
    class Data
}
