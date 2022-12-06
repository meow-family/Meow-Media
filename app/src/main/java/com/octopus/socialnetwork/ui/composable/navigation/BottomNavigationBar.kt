package com.octopus.socialnetwork.ui.composable.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.octopus.socialnetwork.ui.composable.SpaceHorizontally32dp
import com.octopus.socialnetwork.ui.theme.heightBottomAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
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

            items[0].let { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                    icon = { ComposableNavigationIcon(item) },
                    label = { if (selected) Text("━━") else Text("") }

                )
            }

            items[1].let { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                    icon = { ComposableNavigationIcon(item) },
                    label = { if (selected) Text("━━") else Text("") }

                )
            }

            SpaceHorizontally32dp()
            SpaceHorizontally32dp()

            items[2].let { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                    icon = { ComposableNavigationIcon(item) },
                    label = { if (selected) Text("━━") else Text("") }
                )
            }

            items[3].let { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.onSecondary,
                    unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                    icon = { ComposableNavigationIcon(item) },
                    label = { if (selected) Text("━━") else Text("") }

                )
            }
        }
    }

}
