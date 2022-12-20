package com.octopus.socialnetwork.ui.composable.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.octopus.socialnetwork.R



@Composable
fun LottieSearch() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search))
    Box {
        Box(
            modifier = Modifier.padding(vertical = 124.dp).fillMaxSize()
                .align(alignment = Alignment.Center).padding(32.dp)
        ) {
            LottieAnimation(composition = composition, iterations = Int.MAX_VALUE,
                modifier = Modifier.background(color = MaterialTheme.colors.onPrimary,
                shape = RoundedCornerShape(16.dp))
            )
        }
    }

}