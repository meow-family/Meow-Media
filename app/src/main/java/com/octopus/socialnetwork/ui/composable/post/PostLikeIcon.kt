package com.octopus.socialnetwork.ui.composable.post

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.post.state.PostUiState
import com.octopus.socialnetwork.ui.theme.*

@Composable
fun setLikeColor(isLiked: Boolean, isComment: Boolean = false): Color {
    val iconColor by animateColorAsState(
        targetValue = if (isLiked) MaterialTheme.colors.primary else {
            if (isComment) Color.Gray else Color.White
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow)
    )
    return iconColor
}


@Composable
fun setLikeSize(isLiked: Boolean) : Dp {
    val iconSize by animateDpAsState(
        targetValue = if (isLiked) 16.5.dp else 16.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow)
    )
    return iconSize
}


@Composable
fun InteractionLikeIcon(
    post: PostUiState,
    onClickLike: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .clip(Shapes.large)
            .background(color = LightBlack_65),
    ) {

        Column(
            modifier = Modifier.padding(
                horizontal = spacingSmall,
                vertical = spacingXSmall
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick =  onClickLike,
                modifier = modifier.size(setLikeSize(post.isLiked))
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cat_foot),
                    contentDescription = stringResource(R.string.like),
                    tint = setLikeColor(post.isLiked)
                )
            }

            Text(
                text = post.likeCount,
                color = Color.White,
                style = MaterialTheme.typography.caption
            )
        }
    }

}