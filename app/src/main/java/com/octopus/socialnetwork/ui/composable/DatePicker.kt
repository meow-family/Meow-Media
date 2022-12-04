package com.octopus.socialnetwork.ui.composable

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.octopus.socialnetwork.R

@Composable
fun rememberDatePicker(onChangeBirthday: (String) -> Unit): DatePickerDialog {
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        R.style.DatePickerDialogTheme,
        { _, year: Int, month: Int, dayOfMonth: Int ->
            onChangeBirthday("$dayOfMonth/$month/$year")
        },
        2022, 5, 1
    )
    return remember { datePickerDialog }
}