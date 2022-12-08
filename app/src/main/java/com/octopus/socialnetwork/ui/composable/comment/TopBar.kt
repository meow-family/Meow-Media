package com.octopus.socialnetwork.ui.composable.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.shadowLightBlack

@Composable
fun TopBar(title : String) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = "print",
                tint = Color.Black
            )
        }

        Title(text = title)
    }
}

@Composable
fun Title(text: String) {
    Text(
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
}