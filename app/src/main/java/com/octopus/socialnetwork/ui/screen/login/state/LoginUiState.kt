package com.octopus.socialnetwork.ui.screen.login.state

data class LoginUiState(
    val username : String ="",
    val password : String = "",
    val isError : Boolean = false,
    val errorMessage : String = "",
    var showPassword: Boolean = false,

    )
