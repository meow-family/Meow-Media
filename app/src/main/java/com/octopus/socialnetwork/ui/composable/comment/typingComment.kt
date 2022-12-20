package com.octopus.socialnetwork.ui.composable.comment

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentsUiState
import com.octopus.socialnetwork.ui.theme.textPrimaryColor
import com.octopus.socialnetwork.ui.theme.textSecondaryColor


@Composable
fun TypingField(
    modifier: Modifier = Modifier,
    onChangeTypingComment: (String) -> Unit,
    onClickSend: () -> Unit,
    state: CommentsUiState,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(48.dp),
        backgroundColor = MaterialTheme.colors.textSecondaryColor,
        shape = RoundedCornerShape(56.dp),
        elevation = 0.dp
    ) {
        Row(
            Modifier.height(IntrinsicSize.Min)
        ) {

            BasicTextField(
                modifier = Modifier.weight(1f).padding(16.dp),
                value = state.comment,
                maxLines = 10,
                onValueChange = onChangeTypingComment,
                textStyle = TextStyle(color = MaterialTheme.colors.textPrimaryColor, fontSize = 14.sp),
                decorationBox = { innerTextField ->
                    if (state.comment.isEmpty()) {
                        Text(
                            text = stringResource(R.string.your_commit),
                            modifier = Modifier.alpha(.5f),
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            )

            IconButton(
                onClick = onClickSend,
                enabled = state.comment.isNotBlank())
            {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    tint = if (state.comment.isNotBlank()) Color.Red else Color.Gray
                )
            }

        }
    }

}