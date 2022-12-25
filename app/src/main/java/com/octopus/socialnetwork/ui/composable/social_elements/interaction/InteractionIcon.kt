package com.octopus.socialnetwork.ui.composable.social_elements.interaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.IconSize16
import com.octopus.socialnetwork.ui.theme.LightBlack_65
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.spacingSmall
import com.octopus.socialnetwork.ui.theme.spacingXSmall

@Composable
fun InteractionIcon(
    count: String? = null,
    icon: Int = R.drawable.ic_like_16,
    onClick: () -> Unit,
    tint: Color,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .clip(Shapes.large)
            .background(color = LightBlack_65),
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = spacingSmall,
                vertical = spacingXSmall
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { onClick() },
                modifier = modifier
                    .size(IconSize16)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(R.string.like),
                    tint = tint
                )
            }

            count?.let {
                Text(
                    text = count.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.caption
                )
            }


        }
    }

}