package com.octopus.socialnetwork.ui.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.getHourAndMinutes() :
        String = SimpleDateFormat("HH:mm a", Locale("en"))
        .format(this).toString()