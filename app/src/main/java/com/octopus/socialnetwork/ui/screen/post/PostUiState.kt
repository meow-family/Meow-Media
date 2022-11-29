package com.octopus.socialnetwork.ui.screen.post

data class PostUiState(
    val imageUrl: String,
    val isLoading: Boolean = false,
    val error: String = ""
)
