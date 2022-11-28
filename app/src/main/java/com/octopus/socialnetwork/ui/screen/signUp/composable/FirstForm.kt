package com.octopus.socialnetwork.ui.screen.signUp.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R


@Composable
fun FirstForm() {
    var userName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var reEmail by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        InputAuth(
            label = stringResource(R.string.username),
            icon = Icons.Default.Person,
            action = ImeAction.Next,
            value = userName,
            onValueChange = {
                userName = it
            },
        )
        InputAuth(
            label = stringResource(R.string.email),
            icon = Icons.Default.Email,
            action = ImeAction.Next,
            value = email,
            onValueChange = {
                email = it
            },
        )
        InputAuth(
            label = stringResource(R.string.re_email),
            icon = Icons.Default.Email,
            action = ImeAction.Next,
            value = reEmail,
            onValueChange = {
                reEmail = it
            },
        )
        InputAuth(
            label = stringResource(R.string.password),
            icon = Icons.Default.Lock,
            action = ImeAction.Next,
            value = password,
            isPassword = true,
            onValueChange = {
                password = it
            },
        )
    }


}