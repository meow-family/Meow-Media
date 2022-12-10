package com.octopus.socialnetwork.ui.composable.interaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R

@Composable

fun InteractionIcon(
    count: String? = null,
    icon: Painter,
    tint: Color,
    onClick: () -> Unit,

    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(56.dp))
                .background(Color(0x30202020)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onClick) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = icon,
                    contentDescription = stringResource(R.string.like_icon),
                    tint = tint
                )
            }
            count?.let { Text(text = it, color = Color.White, fontSize = 12.sp) }

        }
    }
