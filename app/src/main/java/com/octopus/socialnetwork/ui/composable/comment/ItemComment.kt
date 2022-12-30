package com.octopus.socialnetwork.ui.composable.comment

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.Avatar
import com.octopus.socialnetwork.ui.composable.post.setLikeColor
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentDetailsUiState
import com.octopus.socialnetwork.ui.screen.comments.state.CommentDetailsUiState
import com.octopus.socialnetwork.ui.theme.light_outline
import com.octopus.socialnetwork.ui.util.playMeowSound


@Composable
fun ItemComment(
    state: CommentDetailsUiState,
    modifier: Modifier = Modifier,
    onLike: (Int, Int, Boolean) -> Unit,
) {
    val context = LocalContext.current

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 8.dp, 8.dp, 16.dp)
    ) {

        val (userImage, fullName, userName, postText, like, likeCounter, contentTime) = createRefs()


        Avatar(
            modifier = Modifier
                .clip(CircleShape)
                .constrainAs(userImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            imageUrl = state.userAvatar, size = 50,
            contentDescription = stringResource(id = R.string.profile_image)
        )

        Text(
            text = state.fullName,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(fullName) {
                top.linkTo(userImage.top, 4.dp)
                start.linkTo(userImage.end, 8.dp)
            })
        Text(
            text = state.userName,
            fontSize = 12.sp,
            color = light_outline,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(fullName.bottom, 4.dp)
                start.linkTo(fullName.start)
            })

        Text(text = state.comment,
            fontSize = 12.sp,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .constrainAs(postText) {
                    top.linkTo(userImage.bottom, 16.dp)
                    start.linkTo(userImage.start)
                })

        IconButton(onClick = {
            onLike(state.commentId, state.likeCounter, state.isLikedByUser)
            playMeowSound(state.isLikedByUser, context)
        },
            modifier = Modifier
                .width(12.dp)
                .height(12.dp)
                .constrainAs(like) {
                    top.linkTo(postText.bottom, 14.dp)
                    start.linkTo(postText.start)
                }) {
            Icon(
                painterResource(R.drawable.ic_cat_foot),
                contentDescription = stringResource(id = R.string.like),
                tint = setLikeColor(state.isLikedByUser, true)
            )
        }

        Text(text = state.likeCounter.toString(),
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(likeCounter) {
                    start.linkTo(like.end, 4.dp)
                    top.linkTo(like.top, 4.dp)
                    bottom.linkTo(like.bottom)
                })

        Text(text = state.timeCreated,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(contentTime) {
                    top.linkTo(postText.bottom, 12.dp)
                    start.linkTo(likeCounter.end, 18.dp)
                })

    }
}