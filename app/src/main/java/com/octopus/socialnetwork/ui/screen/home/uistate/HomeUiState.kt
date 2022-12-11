package com.octopus.socialnetwork.ui.screen.home.uistate

import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState

data class HomeUiState(
    val posts: List<PostUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val notificationsCount: Int = 0
)