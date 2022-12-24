package com.octopus.socialnetwork.ui.composable.lotties

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search_list))
    Box {
        Box(
            modifier = Modifier.padding(vertical = 124.dp).fillMaxSize()
                .align(alignment = Alignment.Center).padding(32.dp)
        ) {
            LottieAnimation(composition = composition, iterations = Int.MAX_VALUE,)
        }
    }
}