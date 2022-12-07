package com.octopus.socialnetwork.ui.composable.interaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.octopus.socialnetwork.ui.composable.SpaceVertically8dp

@Composable
fun InteractionGroup(
    interactions: List<@Composable () -> Unit>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.background(Color.Transparent)
    ) {
        interactions.forEach { interactionIcon ->
            interactionIcon()
            SpaceVertically8dp()
        }
    }
}