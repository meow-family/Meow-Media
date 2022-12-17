package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.theme.outLine

@Composable
fun Divider(){
    Divider(
        color = MaterialTheme.colors.outLine,
        modifier = Modifier.fillMaxWidth().height(1.dp).alpha(1f),
    )
}
