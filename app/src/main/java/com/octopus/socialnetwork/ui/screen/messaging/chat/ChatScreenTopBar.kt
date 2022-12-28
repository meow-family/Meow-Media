package com.octopus.socialnetwork.ui.screen.messaging.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ProfileImage
import com.octopus.socialnetwork.ui.composable.underLineBoarder
import com.octopus.socialnetwork.ui.screen.messaging.chat.state.uistate.ChatMainUiState
import com.octopus.socialnetwork.ui.theme.outLine
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

@Composable
fun ChatScreenTopBar(
    state: ChatMainUiState,
    onClickBack: () -> Unit,
    onClickImage: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .underLineBoarder(color = MaterialTheme.colors.outLine, strokeWidth = 1.dp)
            .height(56.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = onClickBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                contentDescription = stringResource(id = R.string.icon_arrow_back),
                tint =  MaterialTheme.colors.textPrimaryColor,
                modifier = Modifier.size(18.dp)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = { onClickImage(state.userId) }),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
                imageUrl = state.profileAvatar,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = state.fullName ,
                    color = MaterialTheme.colors.textPrimaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

        }
    }
}