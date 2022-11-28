package com.octopus.socialnetwork.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState


@Preview(showSystemUi = true)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    LoginContent(
        state = state,
        onChangeUsernameOrEmail = viewModel::onChangeUsernameOrEmail,
        onChangePassword = viewModel::onChangePassword,
        login = viewModel::login,
        signUp = viewModel::signUp
    )
}


@Composable
private fun LoginContent(
    state: LoginUiState,
    onChangeUsernameOrEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    login: () -> Unit,
    signUp: () -> Unit

) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),

        ) {
        LoginImage()

        Text(
            modifier = Modifier.padding(vertical = 24.dp),
            text = stringResource(R.string.login),
            fontSize = 16.sp,
            color = Color.Red,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            modifier = Modifier.padding(bottom = 28.dp),
            text = stringResource(R.string.description),
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
        InputLoginTextField(
            value = state.usernameOrEmail,
            onTextChange = onChangeUsernameOrEmail,
            icon = Icons.Filled.Person,
            placeholder = stringResource(R.string.username_or_email),
            modifire = Modifier
        )
        SpacerVertical16()

        InputLoginTextField(
            value = state.password,
            onTextChange = onChangePassword,
            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_lock_24),
            placeholder = stringResource(R.string.password),
            modifire = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))
        LoginButton(login = login)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp),

            ) {
            Text(
                text = stringResource(R.string.donot_have_account),
                fontSize = 14.sp,
                color = Color.Gray,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
            )
            SpacerVertical16()
            TextSignUpButton(text = stringResource(R.string.signup_here), onClick = signUp)

        }


    }
}