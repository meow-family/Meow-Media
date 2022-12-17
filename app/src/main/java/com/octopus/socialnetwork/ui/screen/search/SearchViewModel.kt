package com.octopus.socialnetwork.ui.screen.search

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import com.octopus.socialnetwork.ui.util.extensions.wrapWithTryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val state = _searchUiState.asStateFlow()

    fun onChangeQuery(newValue: String) {
        _searchUiState.update { it.copy(query = newValue) }
        search(newValue)
    }

    fun search(query: String) {
        wrapWithTryCatch(
            { updateSearchUiState(query = query) },
            { _searchUiState.update { it.copy(isError = false) } })
    }

    private suspend fun updateSearchUiState(query: String) {
        val search = searchUseCase(query = query)
        _searchUiState.update { searchUiState ->
            searchUiState.copy(
                users = search.users.map { it.toUserDetailsUiState() },
                isLoading = false,
                isError = false,
            )
        }
    }
}