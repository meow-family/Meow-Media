package com.octopus.socialnetwork.ui.screen.post.composibale

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R

@Composable
fun BackgroundImage() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.login_background),
        contentScale = ContentScale.FillBounds,
        contentDescription = stringResource(R.string.description)
    )
}

@Composable
fun ProfileImage() {
    Image(
        painter = painterResource(R.drawable.login_background),
        contentDescription = stringResource(R.string.description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
    )
}