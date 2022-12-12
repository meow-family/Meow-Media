package com.octopus.socialnetwork.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.octopus.socialnetwork.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.theme.textThirdColor

@Composable
fun SearchViewItem(query: String, onTextChange: (String) -> Unit) {


    TextField(
        value = query,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(height = 48.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        leadingIcon = { Icon( Icons.Default.Search,
            contentDescription = stringResource(id = R.string.search), modifier = Modifier.size(24.dp),
                tint = Color.Gray)
        },
        placeholder = { Text(text = stringResource(id = R.string.search), style = MaterialTheme.typography.h6.copy(
            color = MaterialTheme.colors.onSecondary)) },
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.textThirdColor,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}

