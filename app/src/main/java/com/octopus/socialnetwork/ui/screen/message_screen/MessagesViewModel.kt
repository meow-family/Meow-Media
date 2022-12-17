package com.octopus.socialnetwork.ui.screen.message_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.messages.GetRecentMessagesListUseCase
import com.octopus.socialnetwork.domain.usecase.messages.GetUnreadMessagesUseCase
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import com.octopus.socialnetwork.ui.screen.message_screen.mapper.toMessageUiState
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val fetchRecentMessages: GetRecentMessagesListUseCase,
    private val getUnreadMessages: GetUnreadMessagesUseCase,
    private val getUserIdUseCase: FetchUserIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()


    init {
        getMessagesDetails()
    }

    private fun getMessagesDetails() {

        try {
            viewModelScope.launch {
                val fromUserId = getUserIdUseCase()

                val recentMessages =
                    fetchRecentMessages().map { it.toMessageUiState() }
                Log.i("MMMMMMMMM", recentMessages.toString())

                for (toUserID in recentMessages.map { it.senderId }) {
                    Log.i("MMMMMMMMM", toUserID.toString())
                    Log.i("MMMMMMMMM", fromUserId.toString())
                    val numberOfUnreadMessage =getUnreadMessages(toUserID).messages.size
                    _state.update {
                        it.copy(unreadMessagesCount = numberOfUnreadMessage)
                    }

                }


                _state.update {
                    it.copy(
                        isFail = false,
                        isLoading = false,
                        messages = recentMessages,

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