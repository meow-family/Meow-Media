package com.octopus.socialnetwork.ui.composable.lotties

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
fun LottieLoading(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_loading_cat))
    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center) {
        LottieAnimation(composition = composition, iterations = Int.MAX_VALUE,
            modifier = Modifier.size(100.dp))
    }
}