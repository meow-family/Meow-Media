package com.octopus.socialnetwork.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileImages(backImageProfile: Int, profileImage: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(216.dp)
    ) {
        Image(
            painter = painterResource(id = backImageProfile),
            contentDescription = "back profile image",
            modifier = Modifier
                .fillMaxWidth()
                .height(188.dp)
        )

        Image(
            painter = painterResource(id = profileImage),
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(86.dp)
                .clip(CircleShape)
                .align(alignment = Alignment.BottomCenter)


        )
    }

}

@Composable
fun SmallImageHolder(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "image",
        modifier = Modifier.size(width = 117.52.dp, height = 101.dp)
    )
}

@Composable
fun LargeImageHolder(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "image",
        modifier = Modifier.size(width = 117.52.dp, height = 190.dp)
    )
}

