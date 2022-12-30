package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R


@Composable
fun Avatar(
    imageUrl: String,
    size: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
     ) {
    ImageNetwork(
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier.size(size.dp),
    )
}


@Composable
fun ImageForEmptyList(
    modifier: Modifier = Modifier,
    textId: Int = R.string.no_items
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().background(color = MaterialTheme.colors.background)
            .padding(vertical = 64.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.no_items),
            contentDescription = null,
            modifier = Modifier.size(128.dp)

        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = stringResource(id = textId),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}