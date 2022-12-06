package com.octopus.socialnetwork.ui.screen.message

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.ui.screen.post.uistate.PostMainUiState

@Composable
fun MessageScreen(
    viewModel: MessageViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    MessageContent(
        state = state,
    )
}

@Composable
private fun MessageContent(
    state: PostMainUiState,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

