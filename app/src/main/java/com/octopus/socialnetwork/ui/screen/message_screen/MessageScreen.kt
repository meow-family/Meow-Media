package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.composable.search.LottieSearch
import com.octopus.socialnetwork.ui.composable.search.SearchItem
import com.octopus.socialnetwork.ui.composable.search.SearchViewItem
import com.octopus.socialnetwork.ui.screen.chat.navigateToChat
import com.octopus.socialnetwork.ui.screen.chat.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.search.state.SearchUiState
import com.octopus.socialnetwork.ui.theme.PoppinsTypography
import com.octopus.socialnetwork.ui.theme.spacingMedium

@Composable
fun MessageScreen(
    navController: NavController,
    viewModel: MessagesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val searchState by viewModel.searchUiState.collectAsState()
    MessageViewContent(
        state = state,
        searchState = searchState,
        onClickMessage = { navController.navigateToChat(it) },
        onChangeText = viewModel::onChangeText,
    )
}

@Composable
fun MessageViewContent(
    state: MessageMainUiState,
    searchState: SearchUiState,
    onClickMessage: (Int) -> Unit,
    onChangeText: (String) -> Unit,
) {

    var isSearchVisible by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = spacingMedium)
                .background(MaterialTheme.colors.background),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                Icon(
                    painter = painterResource(id =
                    if (isSearchVisible) R.drawable.ic_baseline_arrow_back_ios_24
                    else R.drawable.search),
                    contentDescription = stringResource(id = R.string.icon_arrow_back),
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = stringResource(R.string.MessageScreenUpbar),
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.W700,
                fontFamily = PoppinsTypography.body1.fontFamily,
                fontStyle = PoppinsTypography.body1.fontStyle,
                fontSize = PoppinsTypography.body1.fontSize
            )
        }

        Divider()
        SpacerVertical16()

        if(!isSearchVisible){
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                if(state.messages.isEmpty()){
                    item { ImageForEmptyList() }
                } else{
                    itemsIndexed(state.messages) { index, item ->
                        MessageItem(onClickMessage = onClickMessage, state = item)
                        if (index < state.messages.lastIndex) Divider()
                    }
                }
            }
        }


        AnimatedVisibility(
            visible = isSearchVisible,
            enter = slideIn { it -> IntOffset(it.width, 0) },
            exit = slideOut { it -> IntOffset(- it.width, 0) },
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f).background(MaterialTheme.colors.background),
            ) {
                SearchViewItem(state = searchState, onValueChange = onChangeText)
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if(searchState.query.isEmpty()) {
                        item { LottieSearch() }
                    }
                    else {
                        items(searchState.users) { searchItem ->
                            SearchItem(state = searchItem, onClickItem = onClickMessage) }
                    }
                }
            }
        }
    }

    if (state.isLoading) {
        Loading()
    }


}