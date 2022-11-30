package com.octopus.socialnetwork.ui.screen.register.uistate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue


 class TextFieldState(
     private val validator: (String) -> String? = { null },
 ) {
     var text: String by mutableStateOf("")
     var isFocusedDirty: Boolean by mutableStateOf(false)
     private var isFocused: Boolean by mutableStateOf(false)
     private var displayErrors: Boolean by mutableStateOf(false)

     val isValid: Boolean
         get() = isValidator()

     private fun isValidator(): Boolean {
         return !validator(text).isNullOrBlank()
     }

     fun onFocusChange(focused: Boolean) {
         isFocused = focused
         if (focused) isFocusedDirty = true
     }

     fun enableShowErrors() {
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    private fun showErrors() = isValidator() && displayErrors

     fun getError(): String? {
         return if (showErrors()) {
             validator(text)
         } else {
             null
         }
     }
}

fun textFieldStateSaver(state: TextFieldState = TextFieldState()) =
    listSaver<TextFieldState, Any>(save = { listOf(it.text, it.isFocusedDirty) }, restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
        }
    })
