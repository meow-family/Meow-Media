package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.PoppinsTypography
import com.octopus.socialnetwork.ui.theme.Red600
import com.octopus.socialnetwork.ui.theme.White50

@Composable
fun ButtonFollow(onFollow: () -> Unit) {
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) White50 else Red600
    val textColor = if (selected) Red600 else White50
    val text = if (selected) stringResource(id = R.string.requested_icon_name)
    else stringResource(id = R.string.follow_icon_name)
    Button(
        onClick = {
            selected = !selected
            onFollow()
        },
        modifier = Modifier
            .height(25.dp)
            .width(87.dp),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Image(
            painterResource(id =R.drawable.icon_person_add_white),
            contentDescription = stringResource(R.string.icon_person_add),
            modifier = Modifier.size(14.dp).alpha(if (selected) 0f else 1f)
        )

        Text(
            text = text,
            Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.W400,
            fontFamily = PoppinsTypography.overline.fontFamily,
            fontStyle = PoppinsTypography.overline.fontStyle,
            fontSize = PoppinsTypography.overline.fontSize,
            color = textColor,
        )
    }
}