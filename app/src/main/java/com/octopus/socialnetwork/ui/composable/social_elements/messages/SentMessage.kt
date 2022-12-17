package com.octopus.socialnetwork.ui.composable.social_elements.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageUiState

@Composable
fun SentMessage(
  state: MessageUiState,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Card(
            modifier = Modifier.background(Color.Transparent) ,
            shape = AbsoluteRoundedCornerShape(topLeft = 16.dp, topRight = 16.dp, bottomRight = 0.dp, bottomLeft = 16.dp),
            backgroundColor = MaterialTheme.colors.primary,
        ) {
            Text(text = state.message, fontSize = 14.sp, modifier = Modifier.padding(12.dp), color = Color.White)


        }
       Text(text = state.lastSendTime, fontSize = 12.sp,   textAlign = TextAlign.End,
                modifier = Modifier.padding(4.dp), color = Color.Gray,)



    }
}
