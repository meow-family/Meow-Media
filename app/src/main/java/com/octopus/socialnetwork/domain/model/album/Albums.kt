package com.octopus.socialnetwork.domain.model.album

data class Albums(
    val albums: List<Album>,
    val albumDetails: AlbumDetails,
    val profilePhoto: String,
    val coverPhoto: String,
    val count: Int,
    val offset: Int,
)
