package com.octopus.socialnetwork.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.CustomButton
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.composable.TextWithAction
import com.octopus.socialnetwork.ui.screen.register.composable.FirstStepRegistration
import com.octopus.socialnetwork.ui.screen.register.composable.SecondStepRegistration
import com.octopus.socialnetwork.ui.screen.register.composable.StepIndicatorRegistration
import com.octopus.socialnetwork.ui.screen.register.uistate.RegisterUiState
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme
import com.octopus.socialnetwork.ui.theme.lightDividerColor
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge
import com.octopus.socialnetwork.ui.theme.spacingMedium
import com.octopus.socialnetwork.ui.theme.textSecondaryColor
import com.octopus.socialnetwork.ui.theme.textThirdColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()

    RegisterContent(
        state = state, pagerState = pagerState,
        register = viewModel::register,
        tryLogin = viewModel::tryLogin,
        coroutineScope = coroutineScope,
        onChangeUserName = viewModel::onChangeUserName,
        onChangeEmail = viewModel::onChangeEmail,
        onChangeReEmail = viewModel::onChangeReEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangeFirstName = viewModel::onChangeFirstName,
        onChangeLastName = viewModel::onChangeLastName,
        onChangeGender = viewModel::onChangeGender,
        onChangeBirthday = viewModel::onChangeBirthday,
    )
}

@ExperimentalPagerApi
@Composable
private fun RegisterContent(
    state: RegisterUiState,
    pagerState: PagerState,
    register: () -> Unit,
    tryLogin: () -> Unit,
    coroutineScope: CoroutineScope,
    onChangeUserName: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangeReEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onChangeBirthday: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(spacingMedium)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            stringResource(id = R.string.create_account),
            style = MaterialTheme.typography.h4.copy(
                color = MaterialTheme.colors.textSecondaryColor
            )
        )

        Text(
            stringResource(id = R.string.sig_up_note),
            style = MaterialTheme.typography.caption.copy(
                color = MaterialTheme.colors.textThirdColor
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacingExtraLarge),

            Arrangement.Center, verticalAlignment = Alignment.CenterVertically
        ) {

            StepIndicatorRegistration(
                "1",
                (pagerState.currentPage == 0 || pagerState.currentPage == 1)
            )

            Divider(
                modifier = Modifier.width(96.dp), color = lightDividerColor
            )

            StepIndicatorRegistration("2", pagerState.currentPage == 1)

        }
        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.signup),
            contentDescription = "image"
        )
        HorizontalPager(
            count = 2,
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> {
                    FirstStepRegistration(
                        state.userInfoForm,
                        onChangeUserName = onChangeUserName,
                        onChangeEmail = onChangeEmail,
                        onChangeReEmail = onChangeReEmail,
                        onChangePassword = onChangePassword,
                    )
                }

                1 -> {
                    SecondStepRegistration(
                        state.userInfoForm,
                        onChangeFirstName = onChangeFirstName,
                        onChangeLastName = onChangeLastName,
                        onChangeGender = onChangeGender,
                        onChangeBirthday = onChangeBirthday,
                    )
                }

            }

        }

    }

    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomButton(
                text = stringResource(if (pagerState.currentPage == 0) R.string.next else R.string.create_account),
                onClick = {
                    if (pagerState.currentPage == 0) {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        register()
                    }
                }
            )
            SpacerVertical16()
            TextWithAction(
                text = stringResource(R.string.already_have_an_account),
                textAction = stringResource(R.string.login),
                onClick = tryLogin
            )

        }

    }
}


@Preview
@Composable
@ExperimentalPagerApi
fun RegisterScreenPreview() {
    SocialNetworkTheme {
        Surface {
            RegisterScreen()
        }
    }
}