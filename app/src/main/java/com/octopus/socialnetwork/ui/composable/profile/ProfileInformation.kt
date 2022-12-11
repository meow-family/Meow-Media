package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.composable.ImageWithShadow
import com.octopus.socialnetwork.ui.composable.ProfileImage


@Composable
fun ProfileInformation(
    backImageProfile: Painter,
    profileImage: Painter
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(216.dp)
    ) {

        ImageWithShadow(
            modifier = Modifier
                .fillMaxWidth()
                .height(188.dp),
            painter = backImageProfile
        )
        Box(
            modifier = Modifier
                .size(86.dp)
                .align(alignment = Alignment.BottomCenter)
        ) {
            ProfileImage(painter = profileImage,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, color = Color.LightGray, shape = CircleShape)
            )
        }
    }
}








