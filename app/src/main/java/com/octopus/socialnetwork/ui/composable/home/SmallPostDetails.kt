package com.octopus.socialnetwork.ui.composable.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.ui.composable.backgroundVerticalGradientLightBlack
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState


@Composable
fun SmallPostDetails(post: PostUiState) {

    Column(
        modifier = Modifier
            .fillMaxWidth().backgroundVerticalGradientLightBlack()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = post.profileAvatar),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = post.fullName,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = post.postDate,
                color = Color.White,
                fontSize = 10.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = post.postDescription,
            color = Color.White,
            fontSize = 12.sp,
            maxLines = 2,
            textAlign = TextAlign.Start
        )


    }
}