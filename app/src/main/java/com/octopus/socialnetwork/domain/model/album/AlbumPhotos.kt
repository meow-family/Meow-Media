package com.octopus.socialnetwork.domain.model.album


import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.Album
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.ImageList


data class AlbumPhotos(
    val album: Album,
    val list: List<ImageList>
)