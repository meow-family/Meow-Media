package com.octopus.socialnetwork.ui.composable

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Share() {
    val mContext = LocalContext.current
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Social Network App")
        type = "text/plain"
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    mContext.startActivity(Intent.createChooser(sendIntent, null))

}