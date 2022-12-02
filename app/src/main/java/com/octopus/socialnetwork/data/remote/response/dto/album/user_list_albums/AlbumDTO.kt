package com.octopus.socialnetwork.data.remote.response.dto.album.user_list_albums


import com.google.gson.annotations.SerializedName

data class AlbumDTO(
    @SerializedName("albums")
    val albums: List<Album?>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("cover_photo")
    val coverPhoto: String?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("profile_photo")
    val profilePhoto: String?
)