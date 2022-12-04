package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.composable.home.AppName
import com.octopus.socialnetwork.ui.theme.Gray900_2

@Preview()
@Composable
fun TopBarArrow() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadowLightBlack()
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "arrow back",
            modifier = Modifier
                .width(7.dp)
                .height(15.dp)
                .clickable { },
        )
        SpaceHorizontally24dp()
        Text(
            text = "Edit profile",
            fontSize = 14.sp,
            color = Gray900_2,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Medium
        )
    }
}