package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.composable.ImageWithShadow
import com.octopus.socialnetwork.ui.composable.ProfileImage

@Composable
fun ProfileInformation(
    backgroundProfileImageUrl: String,
    profileImageUrl: String,
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
            imageUrl = backgroundProfileImageUrl
        )
        Box(
            modifier = Modifier
                .size(86.dp)
                .align(alignment = Alignment.BottomCenter).clip(CircleShape)
                .border(1.dp, color = Color.LightGray, shape = CircleShape)
        ) {
            ProfileImage(
                imageUrl = profileImageUrl,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}








