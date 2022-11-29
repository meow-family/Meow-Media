package com.octopus.socialnetwork.ui.screen.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.light_outline

@Preview
@Composable
fun PostContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(16.dp, 0.dp, 50.dp, 16.dp)
    ) {
        val userImage = createRef()
        val fullName = createRef()
        val userName = createRef()
        val postText = createRef()
        val like = createRef()
        val likeCounter = createRef()
        val reply = createRef()
        val contentTime = createRef()
        val dividerLine = createRef()
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(CircleShape)
                .constrainAs(userImage) {
                    top.linkTo(parent.top, 32.dp)
                    start.linkTo(parent.start, 16.dp)
                }
        ) {
            image(painterResource(id = R.drawable.static_user_image), size = 50)
        }
        Text(text = "Rawed Sama",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(fullName) {
                top.linkTo(parent.top, 36.dp)
                start.linkTo(userImage.end, 8.dp)
            })
        Text(text = "@asoo__chan",
            fontSize = 12.sp,
            color = light_outline,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(fullName.bottom, 4.dp)
                start.linkTo(fullName.start)
                end.linkTo(fullName.end)
            })

        Text(text = "This is my rabbit it is so cute her name tota but before 4 years she is dead (I am sad for that, and I still miss her)",
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(postText) {
                    top.linkTo(userImage.bottom, 16.dp)
                    start.linkTo(userImage.start)
                })
        Box(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(like) {
                    top.linkTo(postText.bottom, 14.dp)
                    start.linkTo(parent.start, 18.dp)
                }
        ) {
            Icon(
                modifier = Modifier
                    .width(13.dp)
                    .height(12.dp),
                painter = painterResource(R.drawable.ic_heart),
                contentDescription = "print",
                tint = Color.Red
            )
        }

        Text(text = "12",
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .wrapContentSize()
                .alpha(.3f)
                .constrainAs(likeCounter) {
                    top.linkTo(postText.bottom, 12.dp)
                    start.linkTo(parent.start, 36.dp)
                })

        Text(text = "Reply",
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .wrapContentSize()
                .alpha(.3f)
                .constrainAs(reply) {
                    top.linkTo(postText.bottom, 12.dp)
                    start.linkTo(likeCounter.end, 28.dp)
                })

        Text(text = "12h",
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .wrapContentSize()
                .alpha(.3f)
                .constrainAs(contentTime) {
                    top.linkTo(postText.bottom, 12.dp)
                    start.linkTo(reply.end, 18.dp)
                })


        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(.1f)
                .constrainAs(dividerLine) {
                    top.linkTo(like.bottom, 16.dp)
                    start.linkTo(parent.start, 18.dp)
                },
            color = Color.Black,
            thickness = 1.dp
        )

    }
}