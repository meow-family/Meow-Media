package com.octopus.socialnetwork.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CustomButton
import com.octopus.socialnetwork.ui.composable.SpacerVertical32
import com.octopus.socialnetwork.ui.composable.profile.EditTextField
import com.octopus.socialnetwork.ui.composable.profile.ProfileInformation
import com.octopus.socialnetwork.ui.composable.profile.TopBarArrow


@Preview(showSystemUi = true)
@Composable
fun EditProfile() {

    ProfileContent()
}

@Composable
private fun ProfileContent(
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item {
            TopBarArrow()
            ProfileInformation(
                painterResource(id = R.drawable.black),
                painterResource(id = R.drawable.iron_man),
                "Edit Profile"
            )
            EditTextField(
                title = "First name",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            EditTextField(
                title = "Last name",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            EditTextField(
                title = "Email",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            EditTextField(
                title = "Current Password",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            EditTextField(
                title = "New Password",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            EditTextField(
                title = "Confirm New Password",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            SpacerVertical32()
            CustomButton(
                text = stringResource(R.string.save),
                onClick = { }
            )
            SpacerVertical32()
        }


    }
}






