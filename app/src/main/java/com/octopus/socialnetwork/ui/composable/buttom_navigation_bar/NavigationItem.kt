package com.octopus.socialnetwork.ui.composable.buttom_navigation_bar


import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Painter,
)