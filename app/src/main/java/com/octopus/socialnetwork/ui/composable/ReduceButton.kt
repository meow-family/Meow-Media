package com.octopus.socialnetwork.ui.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.zero

@Composable
fun ReduceButton(
    onClick: () -> Unit,
    @StringRes idTitleResource: Int,
    @DrawableRes idIconResource: Int,
    isSelected: Boolean = false,
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(25.dp)
            .wrapContentWidth(),
        shape = Shapes.large,
        contentPadding = PaddingValues(zero),
        colors = ButtonDefaults.buttonColors(
            if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
        )
    ) {
        Image(
            painterResource(idIconResource),
            contentDescription = stringResource(idTitleResource),
            modifier = Modifier.size(16.dp).padding(start = 8.dp)
        )

        Text(
            stringResource(idTitleResource),
            Modifier.padding(start = 4.dp, end = 8.dp),
            style = MaterialTheme.typography.overline,
            color = if (isSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
        )
    }
}