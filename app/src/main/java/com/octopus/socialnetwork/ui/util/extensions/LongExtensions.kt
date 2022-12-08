package com.octopus.socialnetwork.ui.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimeCreatedToDate() : String {
    return SimpleDateFormat("HH:mm a", Locale("en"))
        .format(Date(this * 1000))
}