package com.octopus.socialnetwork.ui.screen.register.uistate

data class TextFieldUiState(
    var text: String = "",
    var error: Int = 0,
    var isValid: Boolean = false
)