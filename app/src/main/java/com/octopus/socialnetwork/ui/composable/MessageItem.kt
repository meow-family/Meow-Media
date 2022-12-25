package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.screen.messaging.conversations.uistate.ConversationUiState
import com.octopus.socialnetwork.ui.theme.PoppinsTypography

@Composable
fun MessageItem(
    onClickMessage: (Int) -> Unit,
    state: ConversationUiState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .clickable { onClickMessage(state.otherUser.userId) }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileImage(
            imageUrl = state.otherUser.profileAvatar,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        SpaceHorizontally8dp()
        Column {
            Text(
                text = state.otherUser.fullName,
                fontWeight = FontWeight.Bold,
                fontFamily = PoppinsTypography.body2.fontFamily,
                fontStyle = PoppinsTypography.body2.fontStyle,
                fontSize = PoppinsTypography.body2.fontSize
            )
            SpaceVertically4dp()
            Text(
                text = state.lastMessage,
                modifier = Modifier.width(230.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light,
                fontFamily = PoppinsTypography.overline.fontFamily,
                fontStyle = PoppinsTypography.overline.fontStyle,
                fontSize = PoppinsTypography.overline.fontSize

            )
        }

        Spacer(
            Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            Text(
                text = state.lastSendTime,
                modifier = Modifier.align(Alignment.End),
                fontWeight = FontWeight.Light,
                fontFamily = PoppinsTypography.overline.fontFamily,
                fontStyle = PoppinsTypography.overline.fontStyle,
                fontSize = PoppinsTypography.overline.fontSize

            )
            SpaceVertically4dp()

        }

    }

}