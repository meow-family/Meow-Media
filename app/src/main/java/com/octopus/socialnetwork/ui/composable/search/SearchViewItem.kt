package com.octopus.socialnetwork.ui.composable.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.octopus.socialnetwork.ui.theme.textPrimaryColor
import com.octopus.socialnetwork.ui.theme.textSecondaryColor

@Composable
fun SearchViewItem(
    query: String,
    onValueChange: (String) -> Unit,
    onClickSearch: () -> Unit = {}
) {
    TextField(
        value = query,
        onValueChange = onValueChange,
        modifier = Modifier.padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(height = 48.dp),
        textStyle = TextStyle(color =  MaterialTheme.colors.textPrimaryColor,
            fontSize = 14.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.size(24.dp).clickable { onClickSearch() },
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (query != "") {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.padding(15.dp).size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(24.dp),

        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.textPrimaryColor,
            cursorColor =  MaterialTheme.colors.textPrimaryColor,
            leadingIconColor =  MaterialTheme.colors.textPrimaryColor,
            trailingIconColor = MaterialTheme.colors.textPrimaryColor,
            backgroundColor = MaterialTheme.colors.textSecondaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}


@Composable
fun ShowSearchView(onClickSearch: () -> Unit) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp)
            .fillMaxWidth().height(height = 48.dp)
            .clickable { onClickSearch() }
            .background(color = MaterialTheme.colors.textSecondaryColor,
                shape = RoundedCornerShape(24.dp))
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
    }

}