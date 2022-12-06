package com.octopus.socialnetwork.ui.screen.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.message.FetchMessagesListUseCase
import com.octopus.socialnetwork.domain.usecase.message.FetchRecentMessagesUseCase
import com.octopus.socialnetwork.domain.usecase.message.FetchSendMessageUseCase
import com.octopus.socialnetwork.domain.usecase.message.FetchUnreadMessagesUseCase
import com.octopus.socialnetwork.ui.screen.post.mapper.asPostUiState
import com.octopus.socialnetwork.ui.screen.post.uistate.PostMainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val fetchMessagesListUseCase: FetchMessagesListUseCase,
    private val fetchRecentMessagesUseCase: FetchRecentMessagesUseCase,
    private val fetchUnreadMessagesUseCase: FetchUnreadMessagesUseCase,
    private val fetchSendMessageUseCase: FetchSendMessageUseCase
) : ViewModel() {

    init {
        getMessagesList(
            messageSenderId = 1,
            messageReceiverId = 2
        )
    }


    private val _state = MutableStateFlow()
    val state = _state.asStateFlow()

    private fun getMessagesList(messageSenderId:Int, messageReceiverId:Int) {
        viewModelScope.launch {
            try {
                val message = fetchMessagesListUseCase(messageSenderId, messageReceiverId).message
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        isError = false,
                        postDetails = post
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isError = true
                    )
                }
            }
        }
    }

}