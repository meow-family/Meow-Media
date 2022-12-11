package com.octopus.socialnetwork.ui.screen.chat.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Long.asHour(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}