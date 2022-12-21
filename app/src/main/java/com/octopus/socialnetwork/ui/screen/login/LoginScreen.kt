package com.octopus.socialnetwork.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CustomButton
import com.octopus.socialnetwork.ui.composable.ImageWithShadow
import com.octopus.socialnetwork.ui.composable.InputTextFieldValidation
import com.octopus.socialnetwork.ui.composable.LoadingDialog
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.composable.SpacerVertical32
import com.octopus.socialnetwork.ui.composable.TextWithAction
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState
import com.octopus.socialnetwork.ui.screen.main.navigateToMain
import com.octopus.socialnetwork.ui.screen.register.navigateToRegister
import com.octopus.socialnetwork.ui.theme.spacingMedium


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LoginContent(
        state = state,
        navController = navController,
        login = viewModel::login,
        onChangeUsername = viewModel::onChangeUsername,
        onChangePassword = viewModel::onChangePassword,
        signUp = { navController.navigateToRegister() },
        onClickShowPassword = viewModel::changePasswordVisibility,
        showErrorValidationInput = viewModel::showErrorValidationInput
    )

}


@Composable
private fun LoginContent(
    state: LoginUiState,
    navController: NavController,
    onChangeUsername: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    login: () -> Unit,
    onClickShowPassword: () -> Unit,
    signUp: () -> Unit,
    showErrorValidationInput: () -> Unit,
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
        InputTextFieldValidation(
            state = state.userInput.userName,
            onChangeValue = onChangeUsername,
            placeholder = stringResource(R.string.username),
            icon = Icons.Default.Person,
            showError = state.isDisplayErrorValidationInputs
        )
        SpacerVertical16()
        InputTextFieldValidation(
            state = state.userInput.password,
            isPassword = !state.showPassword,
            onChangeValue = onChangePassword,
            placeholder = stringResource(R.string.password),
            icon = Icons.Default.Lock,
//            action = ImeAction.Done,
            showError = state.isDisplayErrorValidationInputs
        ) {
            IconButton(onClick = onClickShowPassword) {
                if (state.showPassword) {
                    Icon(Icons.Filled.Visibility, contentDescription = null)
                } else {
                    Icon(Icons.Filled.VisibilityOff, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        SpacerVertical32()
        CustomButton(
            modifier = Modifier.padding(horizontal = spacingMedium),
            text = stringResource(R.string.login),
            onClick = {
                val userInput = state.userInput
                if (userInput.userName.isValid && userInput.password.isValid) {
                    login()
                } else {
                    showErrorValidationInput()
                }
            }
        )
        TextWithAction(
            text = stringResource(R.string.donot_have_account),
            textAction = stringResource(R.string.signup_here),
            onClick = signUp
        )

        if (state.isLoading) {
            LoadingDialog()
        }

    }


    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigateToMain()
        }
    }

}