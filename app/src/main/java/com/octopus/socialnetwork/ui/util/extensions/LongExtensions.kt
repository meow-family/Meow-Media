package com.octopus.socialnetwork.ui.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimeCreatedToDate() : String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale("en"))
        .format(Date(this * 1000))
}