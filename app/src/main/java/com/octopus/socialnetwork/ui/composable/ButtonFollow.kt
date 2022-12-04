package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.PoppinsTypography
import com.octopus.socialnetwork.ui.theme.Red600
import com.octopus.socialnetwork.ui.theme.White50

@Composable
fun ButtonFollow(buttonText:Int,onFollow : ()-> Unit) {
    Button(
        onClick = onFollow,
        modifier = Modifier
            .height(25.dp)
            .width(87.dp),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Red600)
    ) {
        Image(
            painterResource(id = R.drawable.icon_person_add),
            contentDescription = stringResource(R.string.icon_person_add),
            modifier = Modifier.size(14.dp)
        )

        Text(
            text = stringResource(buttonText),
            Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.W400,
            fontFamily = PoppinsTypography.overline.fontFamily,
            fontStyle = PoppinsTypography.overline.fontStyle,
            fontSize = PoppinsTypography.overline.fontSize,
            color = White50,
        )
    }
}