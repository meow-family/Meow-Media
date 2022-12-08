package com.octopus.socialnetwork.ui.screen.message_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.messages.GetMessagesListRecentUseCase
import com.octopus.socialnetwork.domain.usecase.messages.MessageListUseCase
import com.octopus.socialnetwork.domain.usecase.messages.MessagesSendUseCase
import com.octopus.socialnetwork.domain.usecase.messages.UnreadMessagesUseCase
import com.octopus.socialnetwork.ui.screen.message_screen.mapper.toMessageUiStateMapper
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val fetchRecentMessages: GetMessagesListRecentUseCase,
    private val fetchMessagesList: MessageListUseCase,
    private val fetchUnreadMessages: UnreadMessagesUseCase,
    private val fetchMessagesSend: MessagesSendUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()


    init {
        getMessagesDetails(5)
    }

    private fun getMessagesDetails(userId: Int) {

        try {
            viewModelScope.launch {
                val recentMessages =
                    fetchRecentMessages(userId)?.map { it.toMessageUiStateMapper() }

                _state.update {
                    it.copy(
                        isFail = false,
                        isLoading = false,
                        recentMessages = recentMessages!!

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