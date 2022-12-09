package com.octopus.socialnetwork.domain.mapper.album

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumDto
import com.octopus.socialnetwork.domain.model.album.UserAlbum


fun AlbumDto.toAlbum(): UserAlbum {
    return UserAlbum(
        albums = emptyList(),
        count = count ?: 0,
        coverPhoto = coverPhoto ?: "",
        offset = offset ?: 0,
        profilePhoto = profilePhoto ?: ""
    )
}

//fun AlbumPhotosDTO.asAlbumPhotos(): AlbumPhotos {
//    return AlbumPhotos(
//        albumDetails = emptyList(),
//        list = emptyList(),
//    )
//}
