package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.spacing
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

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
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.textPrimaryColor
            ),
        )
        IconButton(onClick = onClick) {
            Text(
                modifier = Modifier.padding(spacing),
                text = textAction,
                style = MaterialTheme.typography.caption
                    .copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    ),
            )
        }

    }

}

@Composable
@Preview(showBackground = true)
private fun TextWithActionPreview() {
    SocialNetworkTheme {
        TextWithAction("Create", "Login") {}
    }
}