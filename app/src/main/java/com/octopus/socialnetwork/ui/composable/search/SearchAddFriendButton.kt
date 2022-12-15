package com.octopus.socialnetwork.ui.composable.search

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally4dp
import com.octopus.socialnetwork.ui.theme.Shapes

@Composable
fun SearchAddFriendButton(
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        shape = Shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(painter = painterResource(id = R.drawable.add_person), contentDescription = null)
            SpaceHorizontally4dp()
            Text(
                text = "Add",
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
}