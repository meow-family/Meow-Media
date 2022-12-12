package com.octopus.socialnetwork.ui.screen.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.SocialNetworkApplication.Companion.userId
import com.octopus.socialnetwork.domain.usecase.messages.chat.GetMessageListUseCase
import com.octopus.socialnetwork.ui.composable.social_elements.messages.ReceivedMessage
import com.octopus.socialnetwork.ui.composable.social_elements.messages.SentMessage
import com.octopus.socialnetwork.ui.screen.message_screen.mapper.toRecentMessagesUiStateMapper
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessageListUseCase: GetMessageListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MessageMainUiState())
    val state = _state.asStateFlow()


    //private val otherUserId = 16

    init {
        getMessagesWithUser(23)
    }

    private fun getMessagesWithUser(otherUserId: Int) {

        try {
            viewModelScope.launch {

                val recentMessages = getMessageListUseCase(16, otherUserId)
                    .map { it.toRecentMessagesUiStateMapper() }
                Log.i("TESTING", recentMessages.toString())

                _state.value.messages.forEach {
                    if (it.senderId == 16) {
                        _state.update {
                            Log.i("TESTING", "me :${ it.messages }")

                            it.copy(
                                isFail = false, isLoading = false,
                                messages = recentMessages, isSentMessage = true
                            )
                        }
                    } else {
                        _state.update {
                            Log.i("TESTING", " resever :${ it.messages }")
                            it.copy(
                                isFail = false, isLoading = false,
                                messages = recentMessages, isSentMessage = false
                            )
                        }
                    }
                }

                _state.update {
                    Log.i("TESTING", "state befor :${ it.messages }")
                    it.copy(
                        isFail = false, isLoading = false,
                        messages = recentMessages
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