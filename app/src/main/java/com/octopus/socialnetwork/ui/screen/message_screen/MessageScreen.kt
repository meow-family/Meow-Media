package com.octopus.socialnetwork.ui.screen.message_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.composable.MessageItem
import com.octopus.socialnetwork.ui.composable.SearchViewItem
import com.octopus.socialnetwork.ui.composable.SpacerVertical16
import com.octopus.socialnetwork.ui.theme.PoppinsTypography

@Preview(showSystemUi = true)
@Composable
fun MessageScreen() {
    MessageViewContent()
}

@Composable
fun MessageViewContent() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column(Modifier.fillMaxWidth()) {

        Text(
            text = stringResource(R.string.MessageScreenUpbar), modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.W700,
            fontFamily = PoppinsTypography.body1.fontFamily,
            fontStyle = PoppinsTypography.body1.fontStyle,
            fontSize = PoppinsTypography.body1.fontSize
        )
        com.octopus.socialnetwork.ui.composable.Divider()
        Divider()
        SpacerVertical16()

        SearchViewItem(textState)

        SpacerVertical16()

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                MessageItem(
                    nameOfSender = "mostafa",
                    lastMessage = "ههه",
                    countMessagesNotSeen = 3,
                    seen = false,
                    time = "3.00"
                )
            }

        }
    }


}