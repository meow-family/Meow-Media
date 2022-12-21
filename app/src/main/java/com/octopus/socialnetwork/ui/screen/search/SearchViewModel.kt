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

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    fun onChangeQuery(newValue: String) {
        _state.update { it.copy(query = newValue) }
        if (_state.value.query == "") {
            _state.update { it.copy(users = emptyList()) }
        }else{
            getUser(_state.value.query)
        }

    }

   private fun getUser(query: String) {
           viewModelScope.launch {
                try {
                    val search = searchUseCase(query = query)
                    _state.update { searchUiState ->
                        searchUiState.copy(
                            users = search.users.map { it.toUserDetailsUiState() },
                            isLoading = false,
                            isError = false,
                        )
                    }
                }catch (e: Exception) {
                    _state.update { it.copy(isError = false) }

                }

           }
    }

}