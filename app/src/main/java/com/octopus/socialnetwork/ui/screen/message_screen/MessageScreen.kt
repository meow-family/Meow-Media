package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.screen.chat.navigateToChat
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.message_screen.utils.asHour
import com.octopus.socialnetwork.ui.theme.PoppinsTypography

@Composable
fun MessageScreen(
    navController: NavController,
    viewModel: MessagesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    MessageViewContent(
        state = state,
        onClickMessage = {navController.navigateToChat(it)},
        onChangeText = viewModel::onChangeText,

    )
}

@Composable
fun MessageViewContent(
    state: MessageMainUiState,
    onClickMessage: (Int) -> Unit,
    onChangeText: (String) -> Unit,
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

        SearchViewItem(text = state.query, onValueChange = onChangeText )

        SpaceVertically24dp()
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {

            itemsIndexed(state.messages) { index, item ->
                MessageItem(onClickMessage = onClickMessage, state = item)
                if (index < state.messages.lastIndex) Divider()
            }


        }
    }

    if (state.isLoading) { Loading() }



}