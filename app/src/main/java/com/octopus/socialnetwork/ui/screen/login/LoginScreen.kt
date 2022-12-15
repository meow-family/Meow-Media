package com.octopus.socialnetwork.ui.screen.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.*
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState
import com.octopus.socialnetwork.ui.screen.main.navigateToMain
import com.octopus.socialnetwork.ui.screen.register.navigateToRegister
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    LoginContent(
        state = state,
        onChangeUsernameOrEmail = viewModel::onChangeUsername,
        onChangePassword = viewModel::onChangePassword,
        login = { scope.launch {
                scope.launch { viewModel.login().join() }.join()
                if (!state.isError) {
                    navController.navigateToMain()
                }
            } },
        signUp = { navController.navigateToRegister() },
        onClickShowPassword = viewModel::changePasswordVisibility
    )

}


@Composable
private fun LoginContent(
    state: LoginUiState,
    onChangeUsernameOrEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    login: () -> Unit,
    onClickShowPassword: () -> Unit,
    signUp: () -> Unit

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState(), reverseScrolling = true)
            .background(MaterialTheme.colors.background),

        ) {
        ImageWithShadow(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .wrapContentSize(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.login_background)
        )

        Text(
            modifier = Modifier.padding(vertical = 24.dp),
            text = stringResource(R.string.login),
            fontSize = 16.sp,
            color = Color.Red,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            modifier = Modifier
                .padding(bottom = 28.dp)
                .padding(horizontal = 42.dp),
            text = stringResource(R.string.description),
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
        InputTextField(
            value = state.username,
            onValueChange = onChangeUsernameOrEmail,
            icon = Icons.Filled.Person,
            placeholder = stringResource(R.string.username_or_email),
            action = ImeAction.Next,
        )
        SpacerVertical16()
        InputTextField(
            modifier = Modifier.padding(bottom = 24.dp),
            value = state.password,
            isPassword = !state.showPassword,
            onValueChange = onChangePassword,
            icon = Icons.Default.Lock,
            trailingIcon = {
                    IconButton(onClick = onClickShowPassword) {
                        if (state.showPassword) {
                            Icon(Icons.Filled.Visibility, contentDescription = null)
                        } else {
                            Icon(Icons.Filled.VisibilityOff, contentDescription = null)
                        }
                    }
            },
            placeholder = stringResource(R.string.password),
            action = ImeAction.Done,
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(R.string.login),
            onClick = login
        )
        TextWithAction(
            text = stringResource(R.string.donot_have_account),
            textAction = stringResource(R.string.signup_here),
            onClick = signUp
        )

    }

    if (state.isLoading) {
        LoadingDialog()
    }
}