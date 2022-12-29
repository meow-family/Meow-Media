package com.octopus.socialnetwork.ui.screen.messaging.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.composable.social_elements.messages.ReceivedMessage
import com.octopus.socialnetwork.ui.composable.social_elements.messages.SentMessage
import com.octopus.socialnetwork.ui.screen.messaging.chat.state.ChatMainUiState
import com.octopus.socialnetwork.ui.screen.profile.navigateToUserProfileScreen
import com.octopus.socialnetwork.ui.util.extensions.lastIndexOrZero

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
        onClickTryAgain = viewModel::onClickTryAgain,
        onClickImage =  navController::navigateToUserProfileScreen
    )
}

@Composable
fun ChatScreenContent(
    state: ChatMainUiState,
    onTextChange: (String) -> Unit,
    onClickBack: () -> Unit,
    onClickSend: () -> Unit,
    onClickImage: (Int) -> Unit,
    onClickTryAgain: () -> Unit
) {
    val listState = rememberLazyListState()

    if (state.isLoading) {
        LottieLoading()
    } else if (state.isFail ) {
        LottieError(onClickTryAgain)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.SpaceEvenly

        ) {

            ChatScreenTopBar(state, onClickBack = onClickBack, onClickImage = onClickImage)

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(.1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = listState
            ) {

                if(state.messages.isEmpty()){
                    item { ImageForEmptyList(modifier = Modifier.padding(vertical = 100.dp)) }
                } else{
                    items(state.messages) { message ->

                        if (message.isSentByMe) {
                            SentMessage(message)
                        } else {
                            ReceivedMessage(message)
                        }
                    }
                }
            }

            TypingMessage(
                state = state,
                onChangeTypingComment = onTextChange,
                onClickSend = onClickSend,
            )
        }

    }

    LaunchedEffect(key1 = state.isSuccess){
        listState.animateScrollToItem(index = state.messages.lastIndexOrZero())
    }


}
