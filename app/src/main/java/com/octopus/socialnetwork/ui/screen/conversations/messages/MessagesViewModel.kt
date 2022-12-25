package com.octopus.socialnetwork.ui.screen.conversations.messages

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.messages.GetRecentMessagesListUseCase
import com.octopus.socialnetwork.domain.usecase.messages.ReceiveMessageUseCase
import com.octopus.socialnetwork.domain.usecase.search.SearchUseCase
import com.octopus.socialnetwork.ui.screen.conversations.chat.mapper.toMessagesUiState
import com.octopus.socialnetwork.ui.screen.conversations.messages.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val fetchRecentMessages: GetRecentMessagesListUseCase,
    private val searchUseCase: SearchUseCase,
    private val receiveMessageUseCase: ReceiveMessageUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()
    private val query = MutableStateFlow("")


    init {
        getMessagesDetails()
        viewModelScope.launch(Dispatchers.IO) {
            receiveMessageUseCase().collect { message ->
                Log.i(
                    "TESTING",
                    "received ${message.message} at ${message.time} from ${message.friendId}, your id is ${message.id}"
                )
                getMessagesDetails()
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            search()
        }
    }

    private fun getMessagesDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val recentMessages = fetchRecentMessages().map { it.toMessagesUiState() }
                _state.update {
                    it.copy(
                        isFail = false, isLoading = false, messages = recentMessages,
                        isSuccess = true
                    )
                }
            } catch (e: Exception) {
                Log.i("TESTING", "$e was catched! in ${this.javaClass.simpleName}")
                _state.update { it.copy(isLoading = false, isFail = true) }
            }
        }

    }

    fun onClickSearch() {
        _state.update { it.copy(isSearchVisible = !it.isSearchVisible) }
    }

    fun onChangeText(newValue: String) {
        _state.update { it.copy(query = newValue) }
        viewModelScope.launch {
            query.emit(newValue)
        }
    }

    private suspend fun search() {
        query.debounce(1500).collect { query ->
            if (_state.value.isSearchVisible && query.isNotEmpty()) {
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

    fun onClickTryAgain() {
        getMessagesDetails()
    }

}