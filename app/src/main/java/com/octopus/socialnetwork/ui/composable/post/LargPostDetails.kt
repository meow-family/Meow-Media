package com.octopus.socialnetwork.ui.composable.post

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.composable.ProfileImage


@Composable
fun LargPostDetails(
    profileImage: Painter,
    fullName: String,
    userName: String,
    postDescription: String
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp, vertical = 16.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
                painter = profileImage,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = fullName,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = userName,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        Text(
            modifier = Modifier,
            text = postDescription,
            color = Color.White, fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

    }
}