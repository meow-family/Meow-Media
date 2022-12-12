package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.message_screen.utils.asHour
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Composable
fun MessageScreen(
    navController: NavController,
    viewModel: MessagesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    MessageViewContent(state = state, onTextChange = viewModel::onTextChange)
}

@Composable
fun MessageViewContent(state: MessageMainUiState, onTextChange: (String) -> Unit) {


    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp)
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.MessageScreenUpbar),
                color = Gray900_2,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
        }
        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(1.dp).alpha(0.2f))
        SpacerVertical16()
        SearchViewItem(query = state.query, onTextChange =  onTextChange)

        SpacerVertical16()

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = state.messages
            ) {
                MessageItem(
                    nameOfSender = it.senderName,
                    lastMessage = it.message,
                    seen = it.viewed,
                    time = it.lastSendTime.asHour(),
                    avatar = it.avatar
                )

            }


        }
    }


}
