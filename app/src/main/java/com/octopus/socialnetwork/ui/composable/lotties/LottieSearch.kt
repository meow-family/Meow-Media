package com.octopus.socialnetwork.ui.composable.lotties

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_cat_search))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)
    ) {
        LottieAnimation(composition = composition, iterations = Int.MAX_VALUE,
            modifier = Modifier.padding(vertical = 64.dp).size(200.dp))
    }
}