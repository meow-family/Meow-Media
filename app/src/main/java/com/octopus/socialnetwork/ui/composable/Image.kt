package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R


@Composable
fun Avatar(modifier: Modifier = Modifier, painter: Painter, size: Int) {
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.size(size.dp),
    )
}


@Composable
fun ImageForEmptyList(modifier: Modifier = Modifier) {
    Box {

        Box(
            modifier = Modifier.padding(32.dp).fillMaxSize().align(alignment = Alignment.Center)
        ) {

            Image(
                painter = painterResource(id = R.drawable.empty_list_image),
                contentDescription = null,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 32.dp).fillMaxSize()
                    .align(alignment = Alignment.Center)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colors.onPrimary),
            )
        }
    }

}