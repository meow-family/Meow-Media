package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.usecase.messages.GetRecentMessagesListUseCase
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.chat.mapper.toMessageUiState
import com.octopus.socialnetwork.ui.screen.chat.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val fetchRecentMessages: GetRecentMessagesListUseCase,
    private val searchUseCase: SearchUseCase,
    private val messagingRepository: MessagingRepository, /*just for test*/
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()


    init {
        getMessagesDetails()
        viewModelScope.launch {
            messagingRepository.onReceiveNotification().collect{
                getMessagesDetails()
            }
        }
    }

    private fun getMessagesDetails() {
        viewModelScope.launch {
            try {
                val recentMessages =
                    fetchRecentMessages()?.map { it.toMessageUiState() } ?: emptyList()
                _state.update {
                    it.copy(isFail = false, isLoading = false, messages = recentMessages)
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isFail = true) }
            }
        }

    }

    fun onClickSearch() {
        _state.update { it.copy(isSearchVisible = !it.isSearchVisible) }
    }

    fun onChangeText(newValue: String) {
        _state.update { it.copy(query = newValue) }
        search(_state.value.query)
    }

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val searchResult =
                    searchUseCase(query = query).users.map { it.toUserDetailsUiState() }
                _state.update { searchUiState ->
                    searchUiState.copy(
                        users = searchResult,
                        isLoading = false,
                        isFail = false,
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isFail = true) }
            }
        }

    }

}