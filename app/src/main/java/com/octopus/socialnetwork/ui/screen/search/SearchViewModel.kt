package com.octopus.socialnetwork.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUser: SearchUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    fun onChangeQuery(newValue: String) {
        _state.update { it.copy(query = newValue, isLoading = true, isError = false) }
        search()
    }

    private var searchJob: Job? = null
    private fun search() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            try {
                val searchResults = searchUser(query = _state.value.query).users.map {
                    it.toUserDetailsUiState()
                }
                _state.update { searchUiState ->
                    searchUiState.copy(
                        users = searchResults,
                        isLoading = false,
                        isError = false,
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isError = true) }
            }
        }
    }

    fun onClickTryAgain() {
        search()
    }
}