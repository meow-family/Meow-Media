package com.octopus.socialnetwork.ui.composable.social_elements.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SentMessage(text: String) {
    Column() {
        Text(text = text, modifier = Modifier.align(Alignment.End).background(Color.Gray))
    }
}