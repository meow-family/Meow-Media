package com.octopus.socialnetwork.ui.composable.buttom_navigation_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.octopus.socialnetwork.ui.theme.heightBottomAppBar
import com.octopus.socialnetwork.ui.theme.spacingExtraLarge
import com.octopus.socialnetwork.ui.theme.zero

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigation(
    itemBottomNav: List<BottomNavItem>,
    navController: NavHostController,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomAppBar(
        modifier = Modifier.height(heightBottomAppBar),
        backgroundColor = MaterialTheme.colors.background,
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            itemBottomNav.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        if (!selected) onItemClick(item)
                    },
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                    icon = { ComposableNavigationIcon(item) },
                    label = { if (selected) Text("━━") else Text("") },
                    modifier = Modifier.padding(
                        start = if (index == 2) spacingExtraLarge else zero,
                        end = if (index == 1) spacingExtraLarge else zero,
                    ),
                )


            }

        }
    }

}
