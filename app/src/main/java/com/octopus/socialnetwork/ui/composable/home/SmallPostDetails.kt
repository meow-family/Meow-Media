package com.octopus.socialnetwork.ui.composable.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
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
import coil.compose.rememberAsyncImagePainter
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally8dp
import com.octopus.socialnetwork.ui.composable.SpaceVertically4dp
import com.octopus.socialnetwork.ui.composable.backgroundVerticalGradientLightBlack
import com.octopus.socialnetwork.ui.screen.post.uistate.PostUiState
import com.octopus.socialnetwork.ui.theme.spacingMedium


@Composable
fun SmallPostDetails(post: PostUiState) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundVerticalGradientLightBlack()
            .padding(spacingMedium),
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
            SpaceHorizontally8dp()
            Text(
                modifier = Modifier.width(if (post.fullName.length > 15) IntrinsicSize.Min else IntrinsicSize.Max),
                maxLines = 1,
                text = post.fullName,
                color = Color.White,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            SpaceHorizontally8dp()
            Text(
                text = post.postDate,
                color = Color.White,
                style = MaterialTheme.typography.overline
            )
        }

        SpaceVertically4dp()

        Text(
            maxLines = 2,
            text = post.postDescription,
            color = Color.White,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Start
        )


    }
}