package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.Shadow

@Preview(widthDp = 360)
@Composable
fun ItemCard() {

    Box(
        modifier = Modifier.height(380.dp).clip(shape = RoundedCornerShape(16.dp))
    ) {

        Image(
            painter = painterResource(R.drawable.login_background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .alpha(10f).align(alignment = Alignment.BottomCenter),
            elevation = 0.dp,
            shape = AbsoluteRoundedCornerShape(bottomLeft = 16.dp, bottomRight = 16.dp),
            backgroundColor = Shadow
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.login_background),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Asia sama",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "3 mins ago",
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "This is my rabbit it is so cute her name kiu kgkg gkmggrkmkgmkmgkmkgrkmgkrgrgrggr lglorg but before 4 years she is dead (I am sad for that, and I still miss her)",
                    color = Color.White,
                    fontSize = 12.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Start
                )

            }
        }

    }
}

