package com.octopus.socialnetwork.ui.composable.friend_requests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.theme.Shapes
import com.octopus.socialnetwork.ui.theme.heightSmallButton
import com.octopus.socialnetwork.ui.theme.widthSmallButton

@Composable
fun FriendRequestButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    borderStroke: BorderStroke,
    backgroundColor: Color,
    textColor: Color,
    widthButton: Dp = widthSmallButton,
) {
    Button(
        onClick = { onClick() },
        modifier
            .width(widthButton)
            .height(heightSmallButton),
        shape = Shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        border = borderStroke
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(
                color = textColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)

        )
    }
}
