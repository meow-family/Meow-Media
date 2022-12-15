package com.octopus.socialnetwork.ui.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ProfileImage
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageUiState
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Composable
fun ChatScreenTopBar(senderName: String, profileImage: String, onClickBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = stringResource(id = R.string.icon_arrow_back),
            tint = Gray900_2,
            modifier = Modifier
                .size(18.dp)
                .clickable { onClickBack() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
             painter = rememberAsyncImagePainter(model = profileImage),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = senderName ,
                    color = Gray900_2,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

        }
    }
}