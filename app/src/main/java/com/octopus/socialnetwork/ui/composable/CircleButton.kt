package com.octopus.socialnetwork.ui.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.zero

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = Shapes.large,
    @DrawableRes idIconResource: Int,
    @StringRes idTitleResource: Int
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        contentPadding = PaddingValues(zero),


        ) {
        Image(
            painterResource(id = idIconResource),
            modifier = iconModifier,
            contentDescription = stringResource(idTitleResource)
        )

    }
}