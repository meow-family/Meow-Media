package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.ImageWithShadow
import com.octopus.socialnetwork.ui.composable.ProfileImage


@Composable
fun ProfileInformation(backImageProfile: Painter, profileImage: Painter, imageStatus: String) {
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
        when (imageStatus) {
            "Profile Screen" -> {
                ProfileImage(
                    painter = profileImage,
                    modifier = Modifier
                        .size(86.dp)
                        .clip(CircleShape)
                        .align(alignment = Alignment.BottomCenter)
                        .zIndex(1f)
                        .border(1.dp, color = Color.LightGray, shape = CircleShape)
                )
            }
            "Edit Profile" -> {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .size(86.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.iron_man),
                        contentDescription = "user's story",
                        modifier = Modifier
                            .size(
                                200.dp
                                    .times(0.6f)
                                    .plus(2.dp)
                            )
                            .clip(CircleShape)
                            .border(1.dp, color = Color.LightGray, shape = CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colors.primary)
                            .padding(5.dp)
                            .align(Alignment.BottomEnd),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colors.background,
                        )
                    }
                }
            }
        }
    }

}




