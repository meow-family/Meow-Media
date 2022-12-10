package com.octopus.socialnetwork.ui.screen.register.uistate

import com.octopus.socialnetwork.R

data class TextFieldUiState(
    var text: String = "",
    var error: Int? = R.string.is_required,
    var isValid: Boolean = false
)

