package com.octopus.socialnetwork.ui.composable.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.*


@Composable
fun DeletionDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    iconId: Int,
    onClickPrimaryAction: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Card(
        shape = Shapes.large,
        modifier = modifier.padding(spacingXSmall, spacing, spacingXSmall, spacingXSmall),
        elevation = spacingSmall
    ) {
        Column {

            Image(
                painter = painterResource(id = iconId),
                modifier = Modifier.padding(top = spacingLarge).height(70.dp).fillMaxWidth(),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(color = Color.Gray),
                contentDescription = stringResource(R.string.icon),
            )

            Column(modifier = Modifier.padding(spacingMedium)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = spacing).fillMaxWidth(),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.textPrimaryColor),
                )
                Text(
                    text = description,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = spacingSmall, start = spacingLarge, end = spacingLarge)
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.textThirdColor
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = spacingXSmall, end = spacingXSmall, bottom = spacingMedium),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                OutlinedButton(
                    onClick = { onClickCancel() },
                    modifier = Modifier.padding(horizontal = spacing).weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.textThirdColor,
                    )
                }


                Button(
                    onClick = { onClickPrimaryAction() },
                    modifier = Modifier.padding(horizontal = spacing).weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.yes),
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
            }
        }
    }
}
