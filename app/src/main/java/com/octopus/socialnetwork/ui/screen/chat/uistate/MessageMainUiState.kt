package com.octopus.socialnetwork.ui.screen.chat.uistate

import com.octopus.socialnetwork.ui.screen.profile.uistate.UserDetailsUiState

data class MessageMainUiState(
    val isFail: Boolean = false,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    var unreadMessagesCount: Int = 0,
    val messages: List<MessageUiState> = emptyList(),
    val lastMessage: String = "",
    val message: String = "",
    val query: String = "",
    val isRefreshing: Boolean = false,
    val users: List<UserDetailsUiState> = emptyList(),
    var isSearchVisible: Boolean = false,
    val userId : Int = 0,
    val fullName: String = "",
    val profileAvatar: String = "",
    )