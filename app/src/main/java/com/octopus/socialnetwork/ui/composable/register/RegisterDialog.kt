package com.octopus.socialnetwork.ui.composable.register

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.theme.SocialNetworkTheme

@Composable
fun RegisterDialog(
    onClickPrimaryAction: () -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        CustomDialog(
            icon = Icons.Default.Email,
            title = stringResource(R.string.create_account_success),
            description = stringResource(R.string.create_account_message),
            actionTitle = stringResource(id = R.string.check_email),
            onClickPrimaryAction = { onClickPrimaryAction() },
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun RegisterDialogPreview() {
    SocialNetworkTheme {
        RegisterDialog {}
    }
}