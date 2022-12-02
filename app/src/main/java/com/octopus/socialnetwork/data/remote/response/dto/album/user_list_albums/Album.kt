package com.octopus.socialnetwork.data.remote.response.dto.album.user_list_albums

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("album")
    val album: AlbumDetails?,
    @SerializedName("image_url")
    val imageUrl: String?
) {
    data class AlbumDetails(
        @SerializedName("access")
        val access: String?,
        @SerializedName("data")
        val data: Data?,
        @SerializedName("description")
        val description: String?,
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
}