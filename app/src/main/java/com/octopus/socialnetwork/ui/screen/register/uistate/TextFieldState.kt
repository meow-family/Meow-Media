package com.octopus.socialnetwork.ui.screen.register.uistate

import android.util.Log


class TextFieldState(
     var state: TextFieldUiState,
     private val validator: (String) -> String? = { null },
 ) {

     val isValid: Boolean
         get() = isValidator()

     private fun isValidator(): Boolean {
         Log.v("ameer","isValidator text ${state.text} ${validator(state.text) == null}")
         return validator(state.text) == null
     }

     fun onFocusChange(focused: Boolean) {
         state.isFocused = focused
         Log.v("ameer","Asdasdasd")
         if (focused) state.isFocusedDirty = true
     }

     fun enableShowErrors() {
         Log.v("ameer","enableShowErrors")
         if (state.isFocusedDirty) {
             Log.v("ameer","enableShowErrors state.displayErrors = ${state.displayErrors} ")
             state.displayErrors = true
             Log.v("ameer","enableShowErrors displayErrors = ${state.displayErrors} ")
         }
     }

      private fun showErrors() = isValid && state.displayErrors

     fun getError(): String? {
         return if (showErrors()) {
             validator(state.text)
         } else {
             null
         }
     }
 }

