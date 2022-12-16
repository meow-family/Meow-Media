package com.octopus.socialnetwork.ui.screen.search.state

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState


data class SearchUiState(
    val users: List<UserDetailsUiState> = emptyList(),
    var query: String = "",
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)