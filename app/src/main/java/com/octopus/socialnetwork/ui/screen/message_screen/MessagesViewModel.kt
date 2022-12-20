package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.messages.GetRecentMessagesListUseCase
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.chat.mapper.toMessageUiState
import com.octopus.socialnetwork.ui.screen.chat.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import com.octopus.socialnetwork.ui.util.extensions.wrapWithTryCatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val fetchRecentMessages: GetRecentMessagesListUseCase,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState = _searchUiState.asStateFlow()

    init {
        getMessagesDetails()
    }

    private fun getMessagesDetails() {
        viewModelScope.launch {
            try {
                val recentMessages =
                    fetchRecentMessages()?.map { it.toMessageUiState() } ?: emptyList()
                _state.update {
                    it.copy(isFail = false, isLoading = false, messages = recentMessages,)
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isFail = true) }
            }
        }

    }


    fun onChangeText(newValue: String) {
        _searchUiState.update { it.copy(query = newValue) }
        search(newValue)
    }

    fun search(query: String) {
        wrapWithTryCatch(
            { updateSearchUiState(query = query) },
            { _searchUiState.update { it.copy(isLoading = false, isError = true) } }
        )
    }

    private suspend fun updateSearchUiState(query: String) {
        val searchResult = searchUseCase(query = query).users.map { it.toUserDetailsUiState() }
        _searchUiState.update { searchUiState ->
            searchUiState.copy(
                users = searchResult,
                isLoading = false,
                isError = false,
            )
        }
    }

}