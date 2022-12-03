package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.octopus.socialnetwork.ui.composable.ImageWithShadow
import com.octopus.socialnetwork.ui.composable.ProfileImage


@Composable
fun ProfileInformation(backImageProfile: Painter, profileImage: Painter) {
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

        ProfileImage(
            painter = profileImage,
            modifier = Modifier
                .size(86.dp)
                .clip(CircleShape)
                .align(alignment = Alignment.BottomCenter)
                .zIndex(1f)
        )
    }

}

