package com.octopus.socialnetwork.ui.screen.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.LoginResponse
import com.octopus.socialnetwork.domain.usecase.authentication.RegisterUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.EmailValidationUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.NameValidationUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.PasswordValidationUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.RequiredValidationUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.UserNameValidationUseCase
import com.octopus.socialnetwork.ui.screen.register.mapper.toEmailUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toInputFieldUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toPasswordUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toUserNameUiState
import com.octopus.socialnetwork.ui.screen.register.uistate.EmailState
import com.octopus.socialnetwork.ui.screen.register.uistate.InputFieldState
import com.octopus.socialnetwork.ui.screen.register.uistate.PasswordState
import com.octopus.socialnetwork.ui.screen.register.uistate.RegisterUiState
import com.octopus.socialnetwork.ui.screen.register.uistate.UserNameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val userNameValidation: UserNameValidationUseCase,
    private val emailValidation: EmailValidationUseCase,
    private val passwordValidation: PasswordValidationUseCase,
    private val nameValidation: NameValidationUseCase,
    private val requiredValidation: RequiredValidationUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    fun register() {
        onLoading()

        viewModelScope.launch {

            try {
                val response =
                    registerUseCase(
                        RegisterUseCase.Params(
                            firstName = state.value.userInfoForm.firstName.text,
                            lastName = state.value.userInfoForm.lastName.text,
                            email = state.value.userInfoForm.email.text,
                            reEmail = state.value.userInfoForm.reEmail.text,
                            gender = state.value.userInfoForm.gender.text,
                            birthDate = state.value.userInfoForm.birthDate.text,
                            userName = state.value.userInfoForm.userName.text,
                            password = state.value.userInfoForm.password.text
                        )
                    )

                when (response) {
                    is LoginResponse.Success -> {
                        onLoading()
                        onSuccess()
                        Log.v("tester", "Success")
                    }

                    is LoginResponse.Failure -> {
                        onLoading()
                        onFailedCreateAccount()
                        Log.v("tester", "Failure ${response.message}")
                    }
                }

            } catch (e: Exception) {

                Log.v("tester", "Failure ")
                onLoading()
                onFailedCreateAccount()
            }
        }
    }

    private fun onLoading() {
        _state.update {
            it.copy(
                isLoading = !_state.value.isLoading,
            )
        }
    }

    private fun onSuccess() {
        _state.update {
            it.copy(
                isSuccess = !_state.value.isSuccess,
            )
        }
    }

    fun onFailedCreateAccount() {
        _state.update {
            it.copy(
                failedCreateAccount = !_state.value.failedCreateAccount
            )
        }
    }

    fun onSuccessCreateAccount() {
        _state.update {
            it.copy(
                isSuccess = false
            )
        }
    }

    fun showError(step: Int) {
        when (step) {
            1 -> {
                _state.update {
                    it.copy(displayErrorsFirstStepRegistration = true)
                }
            }

            2 -> {
                _state.update {
                    it.copy(displayErrorsSecondStepRegistration = true)
                }
            }
        }

    }
    

    fun onChangeUserName(newUsername: String) {
        val userNameValidationState = userNameValidation(newUsername).toUserNameUiState()
        if (userNameValidationState == UserNameState.VALID) {
            usernameState(newUsername, true)
        } else {
            usernameState(newUsername, false, userNameValidationState.message)
        }
    }

    private fun usernameState(username: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    userName = it.userInfoForm.userName.copy(
                        text = username,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangeEmail(newEmail: String) {
        val emailValidation = emailValidation(newEmail).toEmailUiState()
        if (emailValidation == EmailState.VALID) {
            emailState(email = newEmail, isValidInputs = true)
        } else {
            emailState(newEmail, false, emailValidation.message)
        }
    }

    private fun emailState(email: String, isValidInputs: Boolean, error: Int? = null) {

        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    email = it.userInfoForm.email.copy(
                        text = email,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangeReEmail(newReEmail: String) {
        val email = _state.value.userInfoForm.email.text
        val reEmailValidation = emailValidation.confirmEmail(email, newReEmail).toEmailUiState()

        if (reEmailValidation == EmailState.VALID) {
            reEmailState(newReEmail, true)
        } else {
            reEmailState(newReEmail, false, reEmailValidation.message)
        }
    }

    private fun reEmailState(reEmail: String, isValidInputs: Boolean, error: Int? = null) {

        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    reEmail = it.userInfoForm.reEmail.copy(
                        text = reEmail,
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
                userInfoForm = it.userInfoForm.copy(
                    password = it.userInfoForm.password.copy(
                        text = password,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }


    fun onChangeFirstName(newFirstName: String) {
        val nameValidation = nameValidation(newFirstName).toInputFieldUiState()
        if (nameValidation == InputFieldState.VALID) {
            firstNameState(newFirstName, true)
        } else {
            firstNameState(newFirstName, false, nameValidation.message)
        }
    }

    private fun firstNameState(firstName: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    firstName = it.userInfoForm.firstName.copy(
                        text = firstName,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }


    fun onChangeLastName(newLastName: String) {
        val nameValidation = nameValidation(newLastName).toInputFieldUiState()
        if (nameValidation == InputFieldState.VALID) {
            lastNameState(newLastName, true)
        } else {
            lastNameState(newLastName, false, nameValidation.message)
        }
    }

    private fun lastNameState(lastName: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    lastName = it.userInfoForm.lastName.copy(
                        text = lastName,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }




    fun onChangeGender(newGender: String) {
        val genderValidation = requiredValidation(newGender).toInputFieldUiState()
        if (genderValidation == InputFieldState.VALID) {
            genderState(newGender, true)
        } else {
            genderState(newGender, false, genderValidation.message)
        }
    }

    private fun genderState(gender: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    gender = it.userInfoForm.gender.copy(
                        text = gender,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangeBirthday(newBirthday: String) {
        val genderValidation = requiredValidation(newBirthday).toInputFieldUiState()
        if (genderValidation == InputFieldState.VALID) {
            birthdayState(newBirthday, true)
        } else {
            birthdayState(newBirthday, false, genderValidation.message)
        }
    }

    private fun birthdayState(gender: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    birthDate = it.userInfoForm.birthDate.copy(
                        text = gender,
                        error = error,
                        isValid = isValidInputs
                    )
                )
            )
        }
    }


}