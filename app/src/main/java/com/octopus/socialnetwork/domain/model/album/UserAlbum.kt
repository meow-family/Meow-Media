package com.octopus.socialnetwork.domain.model.album


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.album.Album
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumDetails

data class UserAlbum(
    val albums: List<Album>,
    val count: Int,
    val coverPhoto: String,
    val offset: Int,
    val profilePhoto: String
)

