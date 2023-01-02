package com.octopus.socialnetwork.ui.screen.messaging.chat.state

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ChatMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val messages: Flow<PagingData<ChatUiState>> = emptyFlow(),
    val userId: Int = 0,
    val message: String = "",
    val fullName: String = "",
    val profileAvatar: String = "",
)