package com.octopus.socialnetwork.ui.composable.buttom_navigation_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R

@Composable
fun FloatingActionButton(
    modifier: Modifier = Modifier,
    hiddenBoarder: Boolean = false,
    imageVector: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit,

    ) {
    IconButton(onClick = { onClick() }) {
        Box(
            modifier = modifier
                .offset(y = 5.dp)
                .size(65.dp)
                .clip(CircleShape)
                .background(
                    color = if (hiddenBoarder) Color.Transparent else MaterialTheme.colors.background
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colors.primary)
                    .padding(14.dp),
                imageVector = imageVector,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(R.string.add)
            )
        }
    }
}