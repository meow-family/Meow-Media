package com.octopus.socialnetwork.ui.screen.register.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.model.user.ParamRegister
import com.octopus.socialnetwork.domain.usecase.authentication.register.RegisterUseCase
import com.octopus.socialnetwork.domain.usecase.authentication.validation.*
import com.octopus.socialnetwork.ui.screen.register.mapper.toEmailUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toInputFieldUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toPasswordUiState
import com.octopus.socialnetwork.ui.screen.register.mapper.toUserNameUiState
import com.octopus.socialnetwork.ui.screen.register.state.uistate.*
import com.octopus.socialnetwork.ui.util.enums.InputInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: RegisterUseCase,
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

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response =
                    register(
                        ParamRegister(
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

                if (response) {
                    onLoading()
                    onSuccess()
                } else {
                    onLoading()
                    onFailedCreateAccount()
                }

            } catch (e: Exception) {
                onLoading()
                onFailedCreateAccount()
            }
        }
    }

    private fun onLoading() {
        _state.update { it.copy(isLoading = !_state.value.isLoading) }
    }

    private fun onSuccess() {
        _state.update { it.copy(isSuccess = !_state.value.isSuccess) }
    }

    fun onFailedCreateAccount() {
        _state.update { it.copy(failedCreateAccount = !_state.value.failedCreateAccount) }
    }

    fun onSuccessCreateAccount() {
        _state.update { it.copy(isSuccess = false) }
    }

    fun showErrorValidationInput(input: InputInformation) {
        when (input) {
            InputInformation.Account -> {
                _state.update { it.copy(displayErrorsFirstStepRegistration = true) }
            }

            InputInformation.Personal -> {
                _state.update { it.copy(displayErrorsSecondStepRegistration = true) }
            }
        }

    }


    fun onChangeUserName(newUsername: String) {
        val username = newUsername.trim()
        val userNameValidationState = userNameValidation(username).toUserNameUiState()
        if (userNameValidationState == UserNameState.VALID) {
            usernameState(username, true)
        } else {
            usernameState(username, false, userNameValidationState.message)
        }
    }

    private fun usernameState(username: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    userName = it.userInfoForm.userName.copy(
                        text = username, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangeEmail(newEmail: String) {
        val emailNew = newEmail.trim()
        val emailValidation = emailValidation(emailNew).toEmailUiState()
        if (emailValidation == EmailState.VALID) {
            emailState(email = emailNew, isValidInputs = true)
        } else {
            emailState(emailNew, false, emailValidation.message)
        }
        onChangeReEmail(_state.value.userInfoForm.reEmail.text)

        val email = _state.value.userInfoForm.email.text
        val reEmail = _state.value.userInfoForm.reEmail.text

        if (email == reEmail) {
            reEmailState(reEmail, true)
        }
    }

    private fun emailState(email: String, isValidInputs: Boolean, error: Int? = null) {

        _state.update {
            it.copy(
                isValidInputs = isValidInputs,
                userInfoForm = it.userInfoForm.copy(
                    email = it.userInfoForm.email.copy(
                        text = email, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangeReEmail(newReEmail: String) {
        val reEmail = newReEmail.trim()
        val email = _state.value.userInfoForm.email.text
        val reEmailValidation = emailValidation.confirmEmail(email, reEmail).toEmailUiState()

        if (reEmailValidation == EmailState.VALID) {
            reEmailState(reEmail, true)
        } else {
            reEmailState(reEmail, false, reEmailValidation.message)
        }
    }

    private fun reEmailState(reEmail: String, isValidInputs: Boolean, error: Int? = null) {

        _state.update {
            it.copy(
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    reEmail = it.userInfoForm.reEmail.copy(
                        text = reEmail, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangePassword(newPassword: String) {
        val password = newPassword.trim()
        val passwordValidation = passwordValidation(password).toPasswordUiState()
        if (passwordValidation == PasswordState.VALID) {
            passwordState(password, true)
        } else {
            passwordState(password = password, false, passwordValidation.message)
        }
    }

    fun changePasswordVisibility() {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm
                    .copy(showPassword = !it.userInfoForm.showPassword)
            )
        }
    }

    private fun passwordState(password: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    password = it.userInfoForm.password.copy(
                        text = password, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }

    fun onChangeFirstName(newFirstName: String) {
        val firstName = newFirstName.trim()
        val nameValidation = nameValidation(firstName).toInputFieldUiState()
        if (nameValidation == InputFieldState.VALID) {
            firstNameState(firstName, true)
        } else {
            firstNameState(firstName, false, nameValidation.message)
        }
    }

    private fun firstNameState(firstName: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    firstName = it.userInfoForm.firstName.copy(
                        text = firstName, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }


    fun onChangeLastName(newLastName: String) {
        val lastName = newLastName.trim()
        val nameValidation = nameValidation(lastName).toInputFieldUiState()
        if (nameValidation == InputFieldState.VALID) {
            lastNameState(lastName, true)
        } else {
            lastNameState(lastName, false, nameValidation.message)
        }
    }

    private fun lastNameState(lastName: String, isValidInputs: Boolean, error: Int? = null) {
        _state.update {
            it.copy(
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    lastName = it.userInfoForm.lastName.copy(
                        text = lastName, error = error, isValid = isValidInputs
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
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    gender = it.userInfoForm.gender.copy(
                        text = gender, error = error, isValid = isValidInputs
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
                isValidInputs = isValidInputs, userInfoForm = it.userInfoForm.copy(
                    birthDate = it.userInfoForm.birthDate.copy(
                        text = gender, error = error, isValid = isValidInputs
                    )
                )
            )
        }
    }
}
