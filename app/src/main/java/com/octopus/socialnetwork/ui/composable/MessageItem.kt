package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.PoppinsTypography

@Composable
fun MessageItem(
    nameOfSender: String,
    lastMessage: String,
    countMessagesNotSeen: Int,
    seen: Boolean,
    time: String
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ProfileImage(
                painter = painterResource(id = R.drawable.profile_image),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            SpaceHorizontally8dp()
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = nameOfSender,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontFamily = PoppinsTypography.body2.fontFamily,
                    fontStyle = PoppinsTypography.body2.fontStyle,
                    fontSize = PoppinsTypography.body2.fontSize
                )
                SpaceVertically4dp()
                Text(
                    text = lastMessage,
                    modifier = Modifier.align(Alignment.Start),
                    fontWeight = FontWeight.Light,
                    fontFamily = PoppinsTypography.overline.fontFamily,
                    fontStyle = PoppinsTypography.overline.fontStyle,
                    fontSize = PoppinsTypography.overline.fontSize

                )
            }
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                Text(
                    text = time,
                    modifier = Modifier.align(Alignment.End),
                    fontWeight = FontWeight.Light,
                    fontFamily = PoppinsTypography.overline.fontFamily,
                    fontStyle = PoppinsTypography.overline.fontStyle,
                    fontSize = PoppinsTypography.overline.fontSize

                )
                SpaceVertically4dp()
                if (seen) {
                    Text(
                        text = "Seen",
                        modifier = Modifier.align(Alignment.End),
                        fontWeight = FontWeight.Light,
                        fontFamily = PoppinsTypography.overline.fontFamily,
                        fontStyle = PoppinsTypography.overline.fontStyle,
                        fontSize = PoppinsTypography.overline.fontSize
                    )

                } else {

                    CircleShapeWithText(numberOfMessages = countMessagesNotSeen)
                }

            }

        }
        Divider()

    }

}