package com.octopus.socialnetwork.ui.composable.buttom_navigation_bar

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.octopus.socialnetwork.ui.theme.sizeDefaultIcon

@Composable
fun ComposableNavigationIcon(item: BottomNavItem) {
    Icon(
        painter = item.icon,
        contentDescription = item.name,
        modifier = Modifier.size(sizeDefaultIcon),
    )
}