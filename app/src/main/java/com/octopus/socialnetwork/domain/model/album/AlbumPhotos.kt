package com.octopus.socialnetwork.domain.model.album


import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumDetailsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.ImageList


data class AlbumPhotos(
    val albumDetails: AlbumDetailsDto,
    val list: List<ImageList>
)