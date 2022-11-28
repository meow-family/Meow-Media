package com.octopus.socialnetwork.ui.screen.login

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.login.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel(){

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()


    fun onChangeUsernameOrEmail(newValue: String){
        _state.update { it.copy(usernameOrEmail = newValue) }
    }
    fun onChangePassword(newValue: String){
        _state.update { it.copy(password = newValue) }
    }

    fun login(){
     //
    }
    fun signUp(){
        //
    }
}