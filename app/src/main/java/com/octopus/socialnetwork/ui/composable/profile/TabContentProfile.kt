package com.octopus.socialnetwork.ui.composable.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.octopus.socialnetwork.ui.composable.underLineBoarder
import com.octopus.socialnetwork.ui.screen.profile.state.uistate.ProfileContentTab
import com.octopus.socialnetwork.ui.theme.outLine
import com.octopus.socialnetwork.ui.theme.fontSizePrimary
import com.octopus.socialnetwork.ui.theme.textPrimaryColor

@Composable
fun TabContentProfile(
    state: ProfileContentTab,
    activeTabState: Int,
    onChangeTabIndex: (index: Int) -> Unit,
) {
    TabRow(
        modifier = Modifier
            .underLineBoarder(
                color = MaterialTheme.colors.outLine,
                strokeWidth = 1.dp
            ),
        selectedTabIndex = activeTabState,
        backgroundColor = Color.Transparent,
        indicator = {
            Box {}
        }
    ) {
        state.itemTabs.forEachIndexed { index, text ->
            val selected = activeTabState == index
            Tab(
                selectedContentColor = MaterialTheme.colors.primary,
                modifier = if (selected) Modifier
                    .underLineBoarder(
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 2.dp
                    )
                else Modifier,

                selected = selected,
                onClick = { onChangeTabIndex(index) },
                text = {
                    Text(
                        text = stringResource(text),
                        color = if (selected) MaterialTheme.colors.primary
                        else MaterialTheme.colors.textPrimaryColor,
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = fontSizePrimary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            )
        }
    }
}