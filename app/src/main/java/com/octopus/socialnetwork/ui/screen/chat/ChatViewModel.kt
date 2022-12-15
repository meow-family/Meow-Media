package com.octopus.socialnetwork.ui.screen.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.messages.chat.GetMessageListUseCase
import com.octopus.socialnetwork.ui.screen.message_screen.mapper.toMessageUiState
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessageListUseCase: GetMessageListUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()


    private val otherUserId = 23

    init {
        getMessagesWithUser(otherUserId)
    }

    fun onTextChange(newValue: String) {
        _state.update { it.copy(message = newValue) }
    }

    private fun getMessagesWithUser(otherUserId: Int) {
        try {
            viewModelScope.launch {
                val messages = getMessageListUseCase(otherUserId).map { it.toMessageUiState() }

                messages.find{ it.senderId.equals(otherUserId) }?.let {
                    _state.update { it.copy(senderName = it.senderName, avatar = it.avatar) }
                }

                _state.update { it.copy(isFail = false, isLoading = false, messages = messages,
                    senderName = messages.first().senderName)
                }

            }
        } catch (e: Exception) {
            _state.update { it.copy(isLoading = false, isFail = true) }
        }

    }
}