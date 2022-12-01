package com.octopus.socialnetwork.ui.screen.home.uistate

import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState

data class HomeUiState(
    val posts: List<PostUiState> = emptyList(),
)
