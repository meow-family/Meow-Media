package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.spacingLarge

@Composable
fun TextFieldError(textError: String) {
    Text(
        text = textError,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = spacingLarge, end = spacingLarge, top = 2.dp),
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.error
    )
}