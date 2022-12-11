package com.octopus.socialnetwork.ui.screen.message_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.messages.GetMessageListUseCase
import com.octopus.socialnetwork.domain.usecase.messages.GetRecentMessagesListUseCase
import com.octopus.socialnetwork.domain.usecase.messages.GetUnreadMessagesUseCase
import com.octopus.socialnetwork.domain.usecase.messages.SendMessagesUseCase
import com.octopus.socialnetwork.ui.screen.message_screen.mapper.toRecentMessagesUiStateMapper
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val fetchRecentMessages: GetRecentMessagesListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()


    val userId = 16

    init {
        getMessagesDetails(userId)
    }

    private fun getMessagesDetails(userId: Int) {

        try {
            viewModelScope.launch {

                val recentMessages =
                    fetchRecentMessages(userId).map { it.toRecentMessagesUiStateMapper() }

                _state.update {
                    it.copy(
                        isFail = false,
                        isLoading = false,
                        recentMessages = recentMessages,

                        )
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isFail = true
                )
            }
        }

    }
}