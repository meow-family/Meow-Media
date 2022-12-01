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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R


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
                onValueChange = onChangeTypingComment
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_send),
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp),
                    tint = Color.Unspecified
                )
            }

        }
    }
}