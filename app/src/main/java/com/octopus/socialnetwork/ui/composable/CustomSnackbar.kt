package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.spacingSmall

@Composable
fun CustomSnackBar(message: String, onClickAction: () -> Unit) {
    Snackbar(
        action = {
            Button(onClick = { onClickAction() }) {
                Text(stringResource(R.string.ok))
            }
        },
        modifier = Modifier.padding(spacingSmall)
    ) {
        Text(
            text = message,
        )
    }
}

