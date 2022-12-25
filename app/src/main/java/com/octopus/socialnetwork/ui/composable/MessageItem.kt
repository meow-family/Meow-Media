package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.PoppinsTypography

@Composable
fun MessageItem(
    onClickMessage: (Int) -> Unit,
    state: com.octopus.socialnetwork.ui.screen.conversations.messages.uistate.MessageUiState,
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
            painter = customImageLoad(imageUrl = state.otherUser.profileAvatar),
            modifier = Modifier.size(48.dp).clip(CircleShape)
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