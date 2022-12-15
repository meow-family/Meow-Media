package com.octopus.socialnetwork.ui.screen.search.state

import com.octopus.socialnetwork.domain.model.search.Search


data class SearchUiState(
    val items: List<Search> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)