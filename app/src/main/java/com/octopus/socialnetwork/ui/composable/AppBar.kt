package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Composable
fun AppBar(
    onClickBack: () -> Unit,
    title: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp)
            .background(color = Color.White),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter =  painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = stringResource(id = R.string.icon_arrow_back),
            tint = Gray900_2,
            modifier = Modifier.size(18.dp).clickable { onClickBack() }
        )


        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            color = Gray900_2,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )
    }
}