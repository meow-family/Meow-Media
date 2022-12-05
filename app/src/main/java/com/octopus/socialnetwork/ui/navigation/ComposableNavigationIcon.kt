package com.octopus.socialnetwork.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R

@Composable
fun ComposableNavigationIcon(item: BottomNavItem, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = item.icon,
            contentDescription = item.name
        )
        if (selected) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(
                modifier = Modifier
                    .width(26.dp)
                    .height(2.dp),
                painter = painterResource(id = R.drawable.line),
                contentDescription = "line",
            )
        }
    }
}