package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge


@Composable
fun SpaceVertically8dp(){
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SpaceVertically10dp(){
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun SpaceVertically24dp(){
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun SpacerVertical16(){
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun SpacerVertical32() {
    Spacer(modifier = Modifier.height(spacingExtraLarge))
}
