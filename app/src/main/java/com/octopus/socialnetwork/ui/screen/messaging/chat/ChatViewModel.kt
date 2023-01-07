package com.octopus.socialnetwork.ui.screen.messaging.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.domain.usecase.messages.chat.GetMessageListUseCase
import com.octopus.socialnetwork.domain.usecase.messages.chat.SendMessagesUseCase
import com.octopus.socialnetwork.domain.usecase.messages.fcm.ReceiveMessageUseCase
import com.octopus.socialnetwork.domain.usecase.user.user_details.FetchUserDetailsUseCase
import com.octopus.socialnetwork.ui.screen.messaging.chat.mapper.toChatUiState
import com.octopus.socialnetwork.ui.screen.messaging.chat.state.ChatMainUiState
import com.octopus.socialnetwork.ui.screen.messaging.chat.state.ChatUiState
import com.octopus.socialnetwork.ui.screen.profile.mapper.toUserDetailsUiState
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessageList: GetMessageListUseCase,
    private val fetchUserDetailS: FetchUserDetailsUseCase,
    private val sendMessage: SendMessagesUseCase,
    private val receiveMessageUseCase: ReceiveMessageUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: ChatScreenArgs = ChatScreenArgs(savedStateHandle)

    private val _state = MutableStateFlow(ChatMainUiState())
    val state = _state.asStateFlow()


    init {
        _state.update { it.copy(userId = args.friendId.toInt()) }
        swipeToRefresh()
        getUserInfo(args.friendId.toInt())
        receiveMessage()
    }

    private fun receiveMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                receiveMessageUseCase().collect { message ->
                    if (message.friendId == args.friendId.toInt() && message.friendId != message.id) {
                        addMessageToChat(message)
                    }
                }
            } catch (e: Exception) {
                Log.i("MESSAGING", "cached this exception $e")
            }

        }
    }

    private fun addMessageToChat(message: NotificationData) {
        val receivedMessage = ChatUiState(
            message = message.message,
            lastSendTime = Date(message.time.toLong()).getHourAndMinutes(),
            isSentByMe = false
        )

        val updatedMessages = _state.value.messages.reversed() + receivedMessage
        _state.update { it.copy(messages = updatedMessages.reversed()) }

    }

    fun onTextChange(newValue: String) {
        _state.update { it.copy(message = newValue) }
    }

    fun swipeToRefresh() {
        viewModelScope.launch {
            _state.update { it.copy(isPagerLoading = true, pagerError = "") }
            try {
                val messages = getMessageList(args.friendId.toInt()).map { it.toChatUiState() }
                _state.update {
                    it.copy(
                        isPagerLoading = false, isLoading = false,
                        isEndOfPager = messages.isEmpty(),
                        messages = (it.messages + messages),
                    )
                }
            } catch (t: Throwable) {
                _state.update {
                    it.copy(
                        isPagerLoading = false, isLoading = false,
                        pagerError = if (_state.value.messages.isNotEmpty()) t.message.toString() else "",
                        error = if (_state.value.messages.isEmpty()) {
                            t.message.toString()
                        } else "",

                        )
                }
            }
        }
    }

    private fun getUserInfo(friendId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userInfo = fetchUserDetailS(friendId).toUserDetailsUiState()
                _state.update {
                    it.copy(fullName = userInfo.fullName, profileAvatar = userInfo.profileAvatar)
                }
            } catch (e: Exception) {
                _state.update { it.copy(isFail = true) }

            }
        }
    }


    fun onClickSend() {
        sendMessage(_state.value.message)
        _state.update { it.copy(message = "") }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val updatedMessages = _state.value.messages.reversed() + ChatUiState(
                    message = message,
                    lastSendTime = Calendar.getInstance().time.getHourAndMinutes()
                )

                _state.update { it.copy(messages = updatedMessages.reversed()) }

                sendMessage(args.friendId.toInt(), message)
                _state.update { it.copy(isLoading = false, isFail = false) }

            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isFail = true) }
            }
        }
    }


    fun onClickTryAgain() {
        swipeToRefresh()
        getUserInfo(args.friendId.toInt())
    }

}