package com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumDetails


data class AlbumPhotosDTO(
    @SerializedName("album")
    val albumDetails: AlbumDetails?,
    @SerializedName("list")
    val images: List<ImageList?>?
)