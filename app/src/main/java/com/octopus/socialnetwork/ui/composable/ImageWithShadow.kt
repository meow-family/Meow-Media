package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R


@Composable
fun ImageWithShadow(imageUrl: String, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        ImageNetwork(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray),
            imageUrl = imageUrl,
            contentDescription = stringResource(R.string.background),
        )

        Box(
            modifier = Modifier
                .backgroundVerticalGradientWhite(
                    MaterialTheme.colors.background
                )
                .align(alignment = Alignment.BottomCenter)
        )

    }
}
