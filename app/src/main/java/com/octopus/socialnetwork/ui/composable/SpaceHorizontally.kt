package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.octopus.socialnetwork.ui.theme.spacing
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.spacingSmall


@Composable
fun SpaceHorizontally4dp(){
    Spacer(modifier = Modifier.width(spacing))
}

@Composable
fun SpaceHorizontally8dp(){
    Spacer(modifier = Modifier.width(spacingSmall))
}

@Composable
fun SpaceHorizontally16dp(){
    Spacer(modifier = Modifier.width(spacingMedium))
}