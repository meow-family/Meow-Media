package com.octopus.socialnetwork.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val state = _searchUiState.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val search = searchUseCase(query)
                _searchUiState.update { searchUiState ->
                    searchUiState.copy(
                        users = search.users.map { it.toUserDetailsUiState() },
                        isLoading = false,
                        isError = false,
                    )
                }
            } catch (e: Exception) {
                _searchUiState.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }

    fun onChangeQuery(newValue: String) {
        _searchUiState.update { it.copy(query = newValue) }
        search(query = newValue)
    }
}