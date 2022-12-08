package com.octopus.socialnetwork.ui.screen.message_screen.uistate

data class MessageUiState(
    val idOfSender: Int = 0,
    val nameOfSender: String = "",
    val timeOfLastSend: Int = 0,
    val avatar: String = "",
    val viewed: String = "",
    val message: String = "",

    )
