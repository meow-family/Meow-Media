package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R


@Composable
fun LoginImage(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .wrapContentSize(Alignment.BottomCenter),
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = stringResource(R.string.background),
            contentScale = ContentScale.Crop,
        )
        ShadowImage()
    }
}