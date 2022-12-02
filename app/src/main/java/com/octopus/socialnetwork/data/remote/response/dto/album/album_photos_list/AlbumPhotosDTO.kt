package com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list

import com.google.gson.annotations.SerializedName


data class AlbumPhotosDTO(
    @SerializedName("album")
    val album: Album?,
    @SerializedName("list")
    val list: List<ImageList?>?
)