package com.octopus.socialnetwork.ui.screen.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.composable.Loading
import com.octopus.socialnetwork.ui.composable.TypingMessage
import com.octopus.socialnetwork.ui.composable.social_elements.messages.ReceivedMessage
import com.octopus.socialnetwork.ui.composable.social_elements.messages.SentMessage
import com.octopus.socialnetwork.ui.screen.chat.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.profile.navigateToUserProfileScreen

@SuppressLint("SuspiciousIndentation")
@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ChatScreenContent(
        state = state,
        onTextChange = viewModel::onTextChange,
        onClickBack = { navController.popBackStack() },
        onClickSend = viewModel::onClickSend,
        onClickImage = { userId -> navController.navigateToUserProfileScreen(userId) }
    )
}

@Composable
fun ChatScreenContent(
    state: MessageMainUiState,
    onTextChange: (String) -> Unit,
    onClickBack: () -> Unit,
    onClickSend: () -> Unit,
    onClickImage: (Int) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceEvenly

    ) {

        if (state.messages.isNotEmpty()) {
            ChatScreenTopBar(
                state.messages.first(),
                onClickBack = onClickBack,
                onClickImage = onClickImage
            )
        }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .weight(.1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            items(state.messages) { message ->

                if (message.isSentByMe) {
                    SentMessage(message)
                } else {
                    ReceivedMessage(message)
                }
            }
        }


        TypingMessage(
            modifier = Modifier.fillMaxWidth(),
            value = state,
            onChangeTypingComment = onTextChange,
            onClickSend = onClickSend,
        )
    }

    if (state.isLoading) {
        Loading()
    }

}
