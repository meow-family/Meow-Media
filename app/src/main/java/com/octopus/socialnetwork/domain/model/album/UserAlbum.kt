package com.octopus.socialnetwork.domain.model.album


import com.octopus.socialnetwork.data.remote.response.dto.album.user_list_albums.Album

data class UserAlbum(
    val albums: List<Album>,
    val count: Int,
    val coverPhoto: String,
    val offset: Int,
    val profilePhoto: String
)