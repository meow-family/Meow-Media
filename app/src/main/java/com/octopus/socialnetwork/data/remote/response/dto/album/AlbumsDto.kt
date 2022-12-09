package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

data class AlbumsDto(
    @SerializedName("albums")
    val albums: List<Album?>?,
    @SerializedName("album")
    val albumDetails: AlbumDetailsDto?,
    @SerializedName("profile_photo")
    val profilePhoto: String?,
    @SerializedName("cover_photo")
    val coverPhoto: String?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
)
