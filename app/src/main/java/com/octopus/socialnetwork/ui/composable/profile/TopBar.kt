package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally16dp
import com.octopus.socialnetwork.ui.composable.shadowLightBlack
import com.octopus.socialnetwork.ui.theme.Gray900_2


@Composable
fun TopBarArrow(title: String, onClickBack: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadowLightBlack()
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(onClick = onClickBack) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(R.string.arrow_back),
                modifier = Modifier
                    .size(20.dp)
                    .clickable { },
            )
        }
        SpaceHorizontally16dp()
        Text(
            text = title,
            fontSize = 16.sp,
            color = Gray900_2,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Medium
        )
    }
}