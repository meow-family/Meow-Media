package com.octopus.socialnetwork.ui.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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

    }
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.messages) {
            if (it.isSentByMe) {
                SentMessage(it.message)
            } else {
                ReceivedMessage(message = it.message)
            }
        }
    }
}
