package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.spacing
import com.octopus.socialnetwork.ui.theme.spacingLarge
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.spacingSmall
import com.octopus.socialnetwork.ui.theme.spacingXSmall
import com.octopus.socialnetwork.ui.theme.textPrimaryColor
import com.octopus.socialnetwork.ui.theme.textThirdColor


@Composable
fun CustomDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    icon: ImageVector,
    actionTitle: String,
    onClickPrimaryAction: () -> Unit,
    cancelTitle: String? = null,
    onClickCancel: () -> Unit = {},
) {
    Card(
        shape = Shapes.large,
        modifier = modifier.padding(
            spacingXSmall,
            spacing,
            spacingXSmall,
            spacingXSmall
        ),
        elevation = spacingSmall
    ) {
        Column {

            Image(
                icon,
                modifier = Modifier
                    .padding(top = spacingLarge)
                    .height(70.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = Color.Gray
                ),
                contentDescription = stringResource(R.string.icon),
            )

            Column(modifier = Modifier.padding(spacingMedium)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = spacing)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.textPrimaryColor
                    ),
                )
                Text(
                    text = description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = spacingSmall,
                            start = spacingLarge,
                            end = spacingLarge
                        )
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.textThirdColor

                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacingSmall,
                        vertical = spacingMedium
                    ),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                if (cancelTitle != null) {
                    TextButton(onClick = {
                        onClickCancel()
                    }) {
                        Text(
                            cancelTitle,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.textThirdColor,
                        )
                    }
                }

                Button(onClick = {
                    onClickPrimaryAction()
                }) {
                    Text(
                        actionTitle,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomDialogPreview() {
    SocialNetworkTheme {
        Surface {
            CustomDialog(
                Modifier, "title", "description",
                Icons.Default.Dashboard, "actionTitle", {}, "cancelTitle"
            )
        }
    }
}
