package com.octopus.socialnetwork.data.remote.response.dto.album


import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("albums")
    val albums: List<Album>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("cover_photo")
    val coverPhoto: String?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("profile_photo")
    val profilePhoto: String?
)