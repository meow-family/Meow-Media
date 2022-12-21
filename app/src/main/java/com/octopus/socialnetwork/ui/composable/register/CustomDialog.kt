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
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.Shapes
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
    checkAction: () -> Unit,
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
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(top = spacingLarge)
                    .height(70.dp)
                    .fillMaxWidth(),

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
                            actionTitle,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.textThirdColor,
                        )
                    }
                }

                Button(onClick = {
                    checkAction()
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