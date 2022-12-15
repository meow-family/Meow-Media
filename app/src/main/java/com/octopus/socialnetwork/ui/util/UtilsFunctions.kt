package com.octopus.socialnetwork.ui.util

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToDate(timeCreated: Long?) : Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale("en"))
    return if(timeCreated != null)
        dateFormat.parse(dateFormat.format(Date(timeCreated * 1000)))!!
    else Date()
}