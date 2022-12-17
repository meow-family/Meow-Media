package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.fontSizePrimary
import com.octopus.socialnetwork.ui.theme.lightOutLineColor
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge
import com.octopus.socialnetwork.ui.theme.spacingSmall

@Composable
fun StepIndicatorRegistration(currentPage: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacingExtraLarge),
        Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {

        ItemStepIndicatorRegistration(
            "1",
            (currentPage == 0 || currentPage == 1)
        )

        Divider(
            modifier = Modifier.width(96.dp), color = lightOutLineColor
        )

        ItemStepIndicatorRegistration("2", currentPage == 1)

    }
}

@Composable
fun ItemStepIndicatorRegistration(text: String, isDone: Boolean) {
    Text(
        text = text, modifier = Modifier
            .background(
                shape = Shapes.large,
                color = if (isDone) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
            )
            .padding(vertical = 3.dp, horizontal = spacingSmall),
        style = TextStyle(
            fontSize = fontSizePrimary,
            color = if (isDone) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary,
        )
    )
}