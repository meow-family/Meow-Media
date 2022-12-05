package com.octopus.socialnetwork.data.remote.response.dto.album

import com.google.gson.annotations.SerializedName

data class AlbumPhotosDto(
    @SerializedName("album")
    val albumDetails: AlbumDetails?,
    @SerializedName("list")
    val images: List<ImageList?>?
)