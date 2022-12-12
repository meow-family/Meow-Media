package com.octopus.socialnetwork.ui.screen.chat

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

//            if (state.isSentMessage) { // if the sender id is the same as the logged in user id
//                SentMessage(state.messages.)
//            } else {
//                ReceivedMessage(it.message)
//            }
    }
    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (state.isSentMessage) {
            Log.i("TESTING", " in SentMessage  :${ state.isSentMessage }")
            items(state.messages) { SentMessage(it.message) }
        } else {
            Log.i("TESTING", " in  ReceivedMessage  :${ state.isSentMessage }")
            items(state.messages) { ReceivedMessage(it.message) }
        }
    }


}
