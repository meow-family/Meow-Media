package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.Black65


@Composable
fun ButtonBack(onBack: ()-> Unit) {
    Button(
        onClick = { onBack },
        modifier = Modifier.size(28.dp).padding(8.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Black65),
    ) {
        Image(
            painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.icon_arrow_back),
        )
    }
}