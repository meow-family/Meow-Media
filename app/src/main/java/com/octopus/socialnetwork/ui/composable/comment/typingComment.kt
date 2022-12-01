package com.octopus.socialnetwork.ui.composable.comment

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TypingComment(
    state: TextFieldValue,
    onChangeTypingComment: (String) -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(48.dp),
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(57.dp),
        elevation = 0.dp
    ) {
        Row(
            Modifier
                .height(IntrinsicSize.Min)
        ) {

            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                value = state.text,
                maxLines = 10,
                onValueChange = onChangeTypingComment,
                decorationBox = { innerTextField ->
                    if (state.text.isEmpty()) {
                        Text(
                            text = "Your comment...",
                            modifier = Modifier.alpha(.5f),
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            )

            IconButton(
                onClick = { /*TODO*/ },
                enabled = state.text.isNotBlank()
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    tint = if (state.text.isNotBlank()) Color.Red else Color.Gray
                )
            }

        }
    }
}