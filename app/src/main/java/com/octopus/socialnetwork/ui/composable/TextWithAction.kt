package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.spacing
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.textSecondaryColor

@Composable
fun TextWithAction(
    text: String,
    textAction: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = spacingMedium),

        ) {
        Text(
            text = text,
            style = MaterialTheme.typography.caption
                .copy(
                    color = MaterialTheme.colors.textSecondaryColor
                ),

            )
        
        Text(
            modifier = Modifier
                .clickable { onClick() }
                .padding(spacing)
                .clip(Shapes.large),
            text = textAction,
            style = MaterialTheme.typography.caption
                .copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                ),
        )

    }


}