package com.octopus.socialnetwork.ui.composable.buttom_navigation_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.zero

@Composable
fun CustomFloatingActionButton(
    modifier: Modifier = Modifier,
    size: Dp = 65.dp,
    hiddenBoarder: Boolean = false,
    imageVector: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colors.background)
            .padding(if (hiddenBoarder) zero else 5.dp),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = zero,
            pressedElevation = zero,
        ),
        backgroundColor = MaterialTheme.colors.primary,
        onClick = onClick,
    ) {
        Icon(
            imageVector = imageVector,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(R.string.add)
        )
    }

}

@Preview
@Composable
private fun CustomFloatingActionButtonPreview() {
    SocialNetworkTheme {
        CustomFloatingActionButton {}
    }
}