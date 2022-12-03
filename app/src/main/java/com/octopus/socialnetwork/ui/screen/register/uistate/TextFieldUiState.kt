package com.octopus.socialnetwork.ui.screen.register.uistate

data class TextFieldUiState(
    var text: String = "",
    var isFocusedDirty: Boolean = false,
    var isFocused: Boolean = false,
    var displayErrors: Boolean = false,
)