package com.octopus.socialnetwork.ui.composable.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.SocialNetworkNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigation(items: List<BottomNavItem>, navController: NavHostController,
                     onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = {}) {
                Box(
                    modifier = Modifier
                        .offset(y = 5.dp)
                        .size(65.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colors.onBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colors.primary)
                            .padding(14.dp),
                        imageVector = Icons.Filled.Add,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = stringResource(R.string.add)
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(91.dp),
                backgroundColor = MaterialTheme.colors.onBackground,
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
                            icon = { ComposableNavigationIcon(item, selected) },
                        )
                    }

                    items[1].let { item ->
                        val selected = item.route == backStackEntry.value?.destination?.route
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { onItemClick(item) },
                            selectedContentColor = MaterialTheme.colors.onSecondary,
                            unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                            icon = { ComposableNavigationIcon(item, selected) },
                        )
                    }

                    Spacer(modifier = Modifier.width(90.dp))

                    items[2].let { item ->
                        val selected = item.route == backStackEntry.value?.destination?.route
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { onItemClick(item) },
                            selectedContentColor = MaterialTheme.colors.onSecondary,
                            unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                            icon = { ComposableNavigationIcon(item, selected) },
                        )
                    }

                    items[3].let { item ->
                        val selected = item.route == backStackEntry.value?.destination?.route
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { onItemClick(item) },
                            selectedContentColor = MaterialTheme.colors.onSecondary,
                            unselectedContentColor = MaterialTheme.colors.secondaryVariant,
                            icon = { ComposableNavigationIcon(item, selected) },
                        )
                    }
                }
            }
        })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.primary)
        )
        SocialNetworkNavGraph(navController = navController)
    }

}
