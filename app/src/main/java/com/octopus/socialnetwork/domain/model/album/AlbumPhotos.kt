package com.octopus.socialnetwork.domain.model.album


import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumDetails
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.ImageList


data class AlbumPhotos(
    val albumDetails: AlbumDetails,
    val list: List<ImageList>
)