package com.octopus.socialnetwork.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.LoginUseCase
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()


    fun onChangeUsername(username: String) {
        _state.update { it.copy(username = username) }
    }

    fun onChangePassword(newValue: String) {
        _state.update { it.copy(password = newValue) }
    }

    fun login() {
        viewModelScope.launch {
            try {
                loginUseCase(_state.value.username, _state.value.password)

            }catch (e:Exception){
               _state.update { it.copy(
                   isError = true,

               ) }
            }

        }
    }
    
}