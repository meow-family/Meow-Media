package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("image_url")
    val coverImage: String?,
    @SerializedName("album")
    val albumDetails: AlbumDetailsDto?,
)
