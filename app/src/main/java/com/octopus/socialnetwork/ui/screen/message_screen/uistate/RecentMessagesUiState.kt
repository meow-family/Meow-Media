package com.octopus.socialnetwork.ui.screen.message_screen.uistate

data class RecentMessagesUiState(
    val idOfSender: Int = 0,
    val nameOfSender: String = "",
    val timeOfLastSend: Long = 0,
    val avatar: String = "",
    val viewed: String = "",
    val message: String = "",

)
