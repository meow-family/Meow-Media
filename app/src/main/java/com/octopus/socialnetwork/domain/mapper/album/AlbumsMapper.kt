package com.octopus.socialnetwork.domain.mapper.album

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumDetailsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.photo.Album
import com.octopus.socialnetwork.domain.model.album.AlbumDetails
import com.octopus.socialnetwork.domain.model.album.Albums


fun AlbumDetailsDto.toAlbumDetails(): AlbumDetails {
    return AlbumDetails(
        id = id ?: 0,
        ownerId = ownerId ?: 0,
        title = title ?: "",
        description = description ?: "",
        privacy = privacy ?: "",
        timeCreated = timeCreated ?: 0,
        type = type ?: "",
        subtype = subtype ?: "",
        fileOSSNPhoto = fileOSSNPhoto ?: "",
        data = data.toString(),
    )
}


fun AlbumsDto.toAlbums(): Albums {

    return Albums(
        albums = albums,
        albumDetails = albumDetails!!.toAlbumDetails(),
        profilePhoto = profilePhoto ?: "",
        coverPhoto = coverPhoto ?: "",
        count = count ?: 0,
        offset = offset ?: 0
    )
}