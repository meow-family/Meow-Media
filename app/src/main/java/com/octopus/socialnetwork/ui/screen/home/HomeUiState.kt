package com.octopus.socialnetwork.ui.screen.home

import com.octopus.socialnetwork.ui.screen.post.PostUiState

data class HomeUiState(
    val posts: List<PostUiState> = emptyList(),
)
