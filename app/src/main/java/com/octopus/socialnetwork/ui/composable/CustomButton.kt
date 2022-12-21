package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.heightDefaultButton


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick ,
        modifier = modifier
            .fillMaxWidth()
            .height(heightDefaultButton),
        shape = Shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(
                color = MaterialTheme.colors.onPrimary
            )
        )
    }
}