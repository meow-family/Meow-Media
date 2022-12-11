package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


@Composable
fun EditProfileInformation(
    backImageProfile: Painter,
    profileImage: Painter,
    onEdit: () -> Unit ,
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
            ProfileImage(painter = profileImage, modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, color = Color.LightGray, shape = CircleShape))

            IconButton(
                onClick = { onEdit },
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.primary)
                    .padding(5.dp)
                    .align(Alignment.BottomEnd)
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








