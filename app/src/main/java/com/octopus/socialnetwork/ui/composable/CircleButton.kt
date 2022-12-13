package com.octopus.socialnetwork.ui.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.zero

@Composable
fun CircleButton(
    onClick: () -> Unit,
    @DrawableRes idIconResource: Int,
    @StringRes idTitleResource: Int
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(25.dp)
            .width(29.dp),
        shape = Shapes.large,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        contentPadding = PaddingValues(zero),


        ) {
        Image(
            painterResource(id = idIconResource),
            contentDescription = stringResource(idTitleResource)
        )

    }
}