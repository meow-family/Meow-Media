package com.octopus.socialnetwork.ui.screen.register.uistate


class TextFieldState(
    var state: TextFieldUiState,
    private val showError: Boolean,
    private val validator: (String) -> Int? = { null },
) {

    private fun isValidator(): Boolean {
        if (validator(state.text) != null) state.error = validator(state.text)!!
        return validator(state.text) == null
    }

    private fun showError(): Boolean = showError && !isValidator()

    fun getError(): Int? {
        return if (showError()) {
            validator(state.text)
        } else {
            null
        }
    }

}

