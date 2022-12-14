package com.octopus.socialnetwork.ui.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ProfileImage
import com.octopus.socialnetwork.ui.composable.comment.TypingComment
import com.octopus.socialnetwork.ui.composable.social_elements.messages.ReceivedMessage
import com.octopus.socialnetwork.ui.composable.social_elements.messages.SentMessage
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.theme.Gray900_2
import com.octopus.socialnetwork.ui.util.extensions.lastIndexOrZero

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

      )
}
@Composable
fun ChatScreenContent(
    state: MessageMainUiState,
    onTextChange: (String) -> Unit,
    onClickBack: () -> Unit,
) {
    val listState = rememberLazyListState()

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth().align(alignment = Alignment.TopCenter)
                .height(56.dp)
                .padding(horizontal = 24.dp)
                .background(color = Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter =  painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = stringResource(id = R.string.icon_arrow_back),
                tint = Gray900_2,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onClickBack()}
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileImage(
                    painter = painterResource(id = R.drawable.login_background),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "fullName",
                        color = Gray900_2,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "userName",
                        color = Gray900_2,
                        fontSize = 12.sp
                    )
                }
            }
        }

        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = listState
        ) {

//            itemsIndexed(state.comments) { index, item ->
//            ItemComment(commentDetails = item, onLike = { onClickLike(item.commentId) })
//            if (index < state.comments.lastIndex)
//                Divider()
//        }


            items(state.messages) {
                if (it.isSentByMe) {
                    SentMessage(it.message)
                } else {
                    ReceivedMessage(message = it.message)
                }
            }
        }

    }


    TypingComment(
    value = "state.comment",
    onChangeTypingComment = onTextChange,
    onClickSend = {},
    listState = listState,
    index = state.messages.lastIndexOrZero(),
    )


}
