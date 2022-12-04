package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.Red600

@Composable
fun ButtonMessage(imageButton:Int,onMessage : ()-> Unit) {
    Button(
        onClick = onMessage,
        modifier = Modifier
            .height(25.dp)
            .width(29.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Red600),
        contentPadding = PaddingValues(0.dp),


        ) {
        Image(
            painterResource(imageButton),
            contentDescription = stringResource(R.string.add_icon_name)
        )
    }
}