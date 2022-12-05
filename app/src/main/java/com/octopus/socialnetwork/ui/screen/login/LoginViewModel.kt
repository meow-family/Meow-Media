package com.octopus.socialnetwork.ui.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.authentication.LoginResponse
import com.octopus.socialnetwork.domain.usecase.authentication.LoginUseCase
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel(){

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()


    fun onChangeUsername(username: String){
        _loginUiState.update { it.copy(username = username) }
    }
    fun onChangePassword(newValue: String){
        _loginUiState.update { it.copy(password = newValue) }
    }

    fun login(){
        viewModelScope.launch {
            val loginResponse = loginUseCase(_loginUiState.value.username, _loginUiState.value.password)

            Log.i("TESTING",loginResponse.toString())
            }
    }
    fun signUp(){
        //
    }
}