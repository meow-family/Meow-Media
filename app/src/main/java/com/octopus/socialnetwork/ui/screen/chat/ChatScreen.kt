package com.octopus.socialnetwork.ui.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.ui.composable.social_elements.messages.ReceivedMessage
import com.octopus.socialnetwork.ui.composable.social_elements.messages.SentMessage
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ChatScreenContent(state = state)
}

@Composable
fun ChatScreenContent(state: MessageMainUiState) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val currentUserId = 23
        state.messages.forEach {
            if (it.senderId == currentUserId) { // if the sender id is the same as the logged in user id
                SentMessage(it.message)
            } else {
                ReceivedMessage(it.message)
            }
        }

    }


}
