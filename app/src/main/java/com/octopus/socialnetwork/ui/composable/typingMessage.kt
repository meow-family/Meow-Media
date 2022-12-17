package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageMainUiState
import com.octopus.socialnetwork.ui.screen.message_screen.uistate.MessageUiState
import com.octopus.socialnetwork.ui.theme.textPrimaryColor
import com.octopus.socialnetwork.ui.theme.textSecondaryColor
import kotlinx.coroutines.launch


@Composable
fun TypingMessage(
    modifier: Modifier = Modifier,
    value: MessageMainUiState,
    onChangeTypingComment: (String) -> Unit,
    onClickSend:() -> Unit,
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
        Row(Modifier.height(IntrinsicSize.Min)) {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                value = value.message,
                maxLines = 10,
                onValueChange = onChangeTypingComment,
                textStyle = TextStyle(color = MaterialTheme.colors.textPrimaryColor, fontSize = 14.sp),
                decorationBox = { innerTextField ->
                    if (value.message.isEmpty()) {
                        Text(
                            text = stringResource(R.string.your_message),
                            modifier = Modifier.alpha(.5f),
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            )

            IconButton(
                onClick = onClickSend,
                enabled = value.message.isNotBlank()
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    tint = if (value.message.isNotBlank()) Color.Red else Color.Gray
                )
            }

        }
    }
}