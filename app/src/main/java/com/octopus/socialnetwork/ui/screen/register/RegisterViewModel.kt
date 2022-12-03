package com.octopus.socialnetwork.ui.screen.register

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.register.uistate.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    fun register() {

    }

    fun showError() {
        _state.update {
            it.copy(displayErrors = true)
        }
    }

    fun validInputs(valid: Boolean) {
        _state.update {
            it.copy(isValidInputs = valid)
        }
    }

    fun tryLogin() {}

    fun onChangeUserName(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    userName = it.userInfoForm.userName.copy(
                        text = newValue
                    )
                )
            )
        }
    }

    fun onChangeLastName(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    lastName = it.userInfoForm.lastName.copy(
                        text = newValue
                    )

                )
            )
        }
    }

    fun onChangeFirstName(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(

                    firstName = it.userInfoForm.firstName.copy(
                        text = newValue
                    )

                )
            )
        }
    }

    fun onChangeEmail(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    email = it.userInfoForm.email.copy(
                        text = newValue
                    )
                )
            )
        }
    }

    fun onChangeReEmail(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    reEmail = it.userInfoForm.reEmail.copy(
                        text = newValue
                    )

                )
            )
        }
    }

    fun onChangePassword(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    password = it.userInfoForm.password.copy(
                        text = newValue
                    )

                )
            )
        }
    }

    fun onChangeGender(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    gender = it.userInfoForm.gender.copy(
                        text = newValue
                    )

                )
            )
        }
    }

    fun onChangeBirthday(newValue: String) {
        _state.update {
            it.copy(
                userInfoForm = it.userInfoForm.copy(
                    birthday = it.userInfoForm.birthday.copy(
                        text = newValue
                    )

                )
            )
        }

    }

}