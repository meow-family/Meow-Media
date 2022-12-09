package com.octopus.socialnetwork.ui.screen.home.uistate


data class AlbumUiState(
    val albums: List<UserAlbumUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
)