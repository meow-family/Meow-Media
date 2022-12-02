package com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("access")
    val access: String?,
    @SerializedName("data")
    val data: Data?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("file:ossn:aphoto")
    val fileOssnAphoto: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("subtype")
    val subtype: String?,
    @SerializedName("time_created")
    val timeCreated: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
) {
    class Data
}
