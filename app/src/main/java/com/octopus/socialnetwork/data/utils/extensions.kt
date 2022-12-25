package com.octopus.socialnetwork.data.utils

import androidx.core.text.HtmlCompat
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.util.Constants
import java.text.SimpleDateFormat
import java.util.*


fun String.removeHtmlEncoding(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}

fun Long?.toFormattedDate(pattern: String = "yyyy-MM-dd HH:mm a", locale: String = "en"): Date {
    val dateFormat = SimpleDateFormat(pattern, Locale(locale))
    return this?.let { dateFormat.parse(dateFormat.format(Date(it * 1000))) } ?: Date()
}

