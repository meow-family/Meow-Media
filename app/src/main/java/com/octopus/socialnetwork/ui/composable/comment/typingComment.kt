package com.octopus.socialnetwork.ui.composable.comment

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import kotlinx.coroutines.launch


@Composable
fun TypingField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeTypingComment: (String) -> Unit,
    onClickSend: suspend () -> Unit,
    listState: LazyListState,
    index: Int
) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
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
                value = value,
                maxLines = 10,
                onValueChange = onChangeTypingComment,
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
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
                onClick = {
                    run {
                        coroutineScope.launch {
                            onClickSend()
                            listState.animateScrollToItem(index = index)
                        }
                    }
                },
                enabled = value.isNotBlank()
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    tint = if (value.isNotBlank()) Color.Red else Color.Gray
                )
            }

        }
    }
}