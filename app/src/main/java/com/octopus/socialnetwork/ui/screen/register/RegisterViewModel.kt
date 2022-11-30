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

    fun register() {}
    fun tryLogin() {}

    fun onChangeUserName(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(userName = newValue)) }
    }

    fun onChangeLastName(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(lastName = newValue)) }
    }

    fun onChangeFirstName(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(firstName = newValue)) }
    }

    fun onChangeEmail(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(email = newValue)) }
    }

    fun onChangeReEmail(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(reEmail = newValue)) }
    }

    fun onChangePassword(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(lastName = newValue)) }
    }

    fun onChangeGender(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(gender = newValue)) }
    }

    fun onChangeBirthday(newValue: String) {
        _state.update { it.copy(userInfoForm = it.userInfoForm.copy(birthday = newValue)) }
    }
}