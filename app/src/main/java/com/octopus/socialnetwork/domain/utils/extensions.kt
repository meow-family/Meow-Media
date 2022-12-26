package com.octopus.socialnetwork.domain.utils

import java.text.SimpleDateFormat
import java.util.*


fun Long?.toFormattedDate(pattern: String = "yyyy-MM-dd HH:mm a", locale: String = "en"): Date {
    val dateFormat = SimpleDateFormat(pattern, Locale(locale))
    return this?.let { dateFormat.parse(dateFormat.format(Date(it * 1000))) } ?: Date()
}

fun Date.getHourAndMinutes() :
        String = SimpleDateFormat("HH:mm a", Locale("en"))
    .format(this).toString()

