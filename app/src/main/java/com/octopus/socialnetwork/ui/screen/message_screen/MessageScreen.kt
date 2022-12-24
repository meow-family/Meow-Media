package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.Divider
import com.octopus.socialnetwork.ui.composable.ImageForEmptyList
import com.octopus.socialnetwork.ui.composable.MessageItem
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.composable.lotties.LottieError
import com.octopus.socialnetwork.ui.composable.lotties.LottieLoading
import com.octopus.socialnetwork.ui.composable.lotties.LottieSearch
import com.octopus.socialnetwork.ui.composable.search.SearchItem
import com.octopus.socialnetwork.ui.composable.search.SearchViewItem
import com.octopus.socialnetwork.ui.composable.search.ShowSearchView
import com.octopus.socialnetwork.ui.screen.chat.navigateToChat
import com.octopus.socialnetwork.ui.screen.chat.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.theme.PoppinsTypography


@Composable
fun MessageScreen(
    navController: NavController,
    viewModel: MessagesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    MessageViewContent(
        state = state,
        onClickMessage = { navController.navigateToChat(it) },
        onChangeText = viewModel::onChangeText,
        onClickSearch = viewModel::onClickSearch,
        onClickTryAgain = viewModel::onClickTryAgain
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MessageViewContent(
    state: MessageMainUiState,
    onClickMessage: (Int) -> Unit,
    onChangeText: (String) -> Unit,
    onClickSearch: () -> Unit,
    onClickTryAgain: () -> Unit,
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Text(
            text = stringResource(R.string.MessageScreenUpbar),
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.W700,
            fontFamily = PoppinsTypography.body1.fontFamily,
            fontStyle = PoppinsTypography.body1.fontStyle,
            fontSize = PoppinsTypography.body1.fontSize
        )

        Divider()
        SpacerVertical16()

        if (state.isLoading) {
            LottieLoading()
        } else if (state.isFail) {
            LottieError(onClickTryAgain)
        } else if (state.isSuccess) {

            AnimatedContent(
                targetState = state.isSearchVisible,
                transitionSpec = {
                    slideIn(tween(300)) { it -> IntOffset(it.width, 0) } with
                            slideOut(tween(300)) { it -> IntOffset(it.width, 0) }
                }
            ) {
                when (it) {
                    true -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                                .background(MaterialTheme.colors.background),
                        ) {
                            SearchViewItem(
                                query = state.query,
                                onValueChange = onChangeText,
                                onClickSearch = onClickSearch
                            )
                            SpacerVertical16()

                            LazyColumn(modifier = Modifier.fillMaxSize()) {

                                when {
                                    state.query.isEmpty() -> {
                                        item { LottieSearch() }
                                    }
                                    state.isLoading -> {
                                        item { LottieLoading() }
                                    }
                                    state.users.isEmpty() -> {
                                        item {
                                            ImageForEmptyList(modifier = Modifier.padding(vertical = 100.dp))
                                        }
                                    }
                                    else -> {
                                        items(state.users) { searchItem ->
                                            SearchItem(
                                                state = searchItem,
                                                onClickItem = onClickMessage
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    false -> {
                        LazyColumn(
                            Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                        ) {
                            item { ShowSearchView(onClickSearch) }
                            if (state.messages.isEmpty()) {
                                item {
                                    ImageForEmptyList(
                                        modifier = Modifier
                                            .padding(vertical = 100.dp)
                                    )
                                }
                            } else {
                                itemsIndexed(state.messages) { index, item ->
                                    MessageItem(onClickMessage = onClickMessage, state = item)
                                    if (index < state.messages.lastIndex) Divider()
                                }
                            }
                        }
                    }
                }

            }

        }
    }

}