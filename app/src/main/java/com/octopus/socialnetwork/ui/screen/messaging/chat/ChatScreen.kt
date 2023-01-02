package com.octopus.socialnetwork.ui.screen.messaging.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
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
    val messages = state.messages.collectAsLazyPagingItems()
    val isEmptyFlow = messages.itemSnapshotList.isEmpty()


    if (state.isLoading) {
        LottieLoading()
    } else if (state.isFail ) {
        LottieError(onClickTryAgain)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .imePadding(),
            verticalArrangement = Arrangement.SpaceEvenly

        ) {

            ChatScreenTopBar(state, onClickBack = onClickBack, onClickImage = onClickImage)

            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(.1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                state = listState,
                reverseLayout = true,
            ) {

                if(isEmptyFlow){
                    item { ImageForEmptyList(modifier = Modifier.padding(vertical = 100.dp)) }
                } else {

                    itemsIndexed(messages) { index, message ->
                            message?.let { chatMessage ->
                                if (chatMessage.isSentByMe) {
                                    SentMessage(chatMessage)
                                } else {
                                    ReceivedMessage(chatMessage)
                                }
                            }
                    }

                    when (messages.loadState.append) {
                        is LoadState.NotLoading -> Unit
                        LoadState.Loading -> {
                            item {  LottieLoading() }
                        }
                        is LoadState.Error -> {
                            item { LottieLoading() }
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
        listState.animateScrollToItem(index = messages.itemSnapshotList.lastIndexOrZero())
    }


}
