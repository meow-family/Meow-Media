package com.octopus.socialnetwork.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.domain.usecase.user.UserRelationUseCase
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserRelationUiState
import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val userRelation: UserRelationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()
    private val query = MutableStateFlow("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            search()
        }

    }

    fun onChangeQuery(newValue: String) {
        _state.update { it.copy(query = newValue) }
        viewModelScope.launch {
            query.emit(newValue)
        }
    }

    private suspend fun search() {
        query.debounce(1500).collect { query ->
            Log.i("TESTING", query)
            try {
                if (query.isNotEmpty()) {
                    val search =
                        searchUseCase(query = query).searchResults.map { it.toUserDetailsUiState() }
                            .map { user ->
                                user.relation =
                                    userRelation(user.userId).toUserRelationUiState()
                                user
                            }
                    updateSearchUiState(search)
                }
            } catch (e: Exception) {
                _state.update { it.copy(isError = false) }

            }
        }


    }


    private fun updateSearchUiState(searchResults: List<UserDetailsUiState>) {
        _state.update { searchUiState ->
            searchUiState.copy(
                users = searchResults,
                isLoading = false,
                isError = false,
            )
        }
    }

    fun onClickTryAgain(queryText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            search()
        }
    }
}