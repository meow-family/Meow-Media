package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun MultiTextStyle(
    modifier: Modifier = Modifier,
    name: String,
    title: String?,
) {
    val separator = " "
    val addressText = buildAnnotatedString {
        appendMedium(name)
        appendMedium(separator)
        addStyle(
            SpanStyle(
                fontSize = MaterialTheme.typography.body1.fontSize
            ),
            start = 0,
            end = "$name$separator".length - 1
        )
        if (!title.isNullOrBlank()) {
            append(title)
        }
    }
    Text(
        modifier = Modifier.padding(end = 10.dp),
        text = addressText,
        style = MaterialTheme.typography.body2,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis
    )
}


fun AnnotatedString.Builder.appendMedium(text: String) {
    append(AnnotatedString(text, SpanStyle(fontWeight = FontWeight.Bold)))
}

fun AnnotatedString.Builder.appendColored(text: String, color: androidx.compose.ui.graphics.Color) {
    append(
        AnnotatedString(
            text,
            SpanStyle(color = color)
        )
    )
}