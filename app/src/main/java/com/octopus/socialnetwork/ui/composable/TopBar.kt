package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R

@Preview()
@Composable
fun TopBar(){
    Row(
        Modifier
            .fillMaxWidth().height(52.dp)
           .background(color = Color.White).padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        AppName(text = stringResource(R.string.octopusyan))
        Icons(imageVector = Icons.Default.Notifications, modifier = Modifier.size(23.dp) )

    }
}