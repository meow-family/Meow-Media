package com.octopus.socialnetwork.ui.util.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.wrapWithTryCatch(
    tryBody: suspend () -> Unit,
    catchBody: (e: Exception) -> Unit,
) {
    try {
        viewModelScope.launch {
            tryBody()
        }
    } catch (e: Exception) {
        catchBody(e)
    }
}