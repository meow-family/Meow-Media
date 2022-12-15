package com.octopus.socialnetwork.ui.composable.social_elements.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SentMessage(
    message: String,

) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier.background(Color.Transparent),
            shape = AbsoluteRoundedCornerShape(topLeft = 16.dp, topRight = 16.dp, bottomRight = 16.dp, bottomLeft = 0.dp),
            backgroundColor = Color.Gray,
        ) {
            Text(text = message, fontSize = 16.sp, modifier = Modifier.padding(16.dp), color = Color.White)
        }

    }
}
