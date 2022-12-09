package com.octopus.socialnetwork.ui.screen.home.uistate

import com.octopus.socialnetwork.domain.model.album.Album

data class UserAlbumUiState(

    val albums: List<Album>,
    val count: Int = 0,
    val coverPhoto: String = "",
    val offset: Int = 0,
    val profilePhoto: String = ""

)