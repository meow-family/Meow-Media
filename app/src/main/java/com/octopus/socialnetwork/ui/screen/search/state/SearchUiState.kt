package com.octopus.socialnetwork.ui.screen.search.state

import com.octopus.socialnetwork.ui.screen.profile.state.uistate.UserDetailsUiState


data class SearchUiState(
    val users: List<UserDetailsUiState> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)