package com.octopus.socialnetwork.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.LoginUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.PasswordValidationUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.UserNameOrEmailValidationUseCase
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toEmailUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toPasswordUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toUserNameUiState
import com.octopus.socialnetwork.ui.screen.register.uistate.EmailState
import com.octopus.socialnetwork.ui.screen.register.uistate.PasswordState
import com.octopus.socialnetwork.ui.screen.register.uistate.UserNameState
import com.octopus.socialnetwork.ui.util.extensions.isEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userNameOrEmailValidation: UserNameOrEmailValidationUseCase,
    private val passwordValidation: PasswordValidationUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()


    fun onChangeUsernameOrEmail(newUsernameOrEmail: String) {
        val usernameOrEmailState = userNameOrEmailValidation(newUsernameOrEmail)
        if (newUsernameOrEmail.isEmail()) {
            val emailState = usernameOrEmailState.toEmailUiState()
            if (emailState == EmailState.VALID) {
                usernameOrEmailState(newUsernameOrEmail, true)
            } else {
                usernameOrEmailState(newUsernameOrEmail, false, emailState.message)
            }
        } else {
            val usernameState = usernameOrEmailState.toUserNameUiState()
            if (usernameState == UserNameState.VALID) {
                usernameOrEmailState(newUsernameOrEmail, true)
            } else {
                usernameOrEmailState(newUsernameOrEmail, false, usernameState.message)
            }
        }

    }

    private fun usernameOrEmailState(
        usernameOrEmail: String,
        isValidInputs: Boolean,
        error: Int? = null
    ) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInput = it.userInput.copy(
                    userNameOrEmail = it.userInput.userNameOrEmail.copy(
                        text = usernameOrEmail,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangePassword(newPassword: String) {
        val passwordValidation = passwordValidation(newPassword).toPasswordUiState()
        if (passwordValidation == PasswordState.VALID) {
            passwordState(newPassword, true)
        } else {
            passwordState(password = newPassword, false, passwordValidation.message)
        }
    }

    private fun passwordState(password: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInput = it.userInput.copy(
                    password = it.userInput.password.copy(
                        text = password, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun login() {
        onLoading()
        val userInput = _state.value.userInput
        viewModelScope.launch {
            try {
                val loginResponse =
                    loginUseCase(userInput.userNameOrEmail.text, userInput.password.text)
                if (loginResponse?.username.isNullOrEmpty()) {
                    _state.update { it.copy(isError = true, isLoading = false) }
                } else {
                    _state.update { it.copy(isError = false, isLoading = false, isSuccess = true) }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isError = true,
                        errorMessage = e.toString(),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun showErrorValidationInput() {
        _state.update { it.copy(isDisplayErrorValidationInputs = true) }

    }

    fun changePasswordVisibility() {
        _state.update { it.copy(showPassword = !it.showPassword) }
    }

    private fun onLoading() {
        _state.update { it.copy(isLoading = !_state.value.isLoading) }
    }
}