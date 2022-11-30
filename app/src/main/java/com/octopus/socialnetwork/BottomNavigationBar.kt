package com.octopus.socialnetwork

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
import androidx.compose.ui.graphics.Color
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.octopus.socialnetwork.ui.Navigation


@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
    navController: NavHostController,
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
                        .background(color = Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colors.primary)
                            .padding(14.dp),
                        imageVector = Icons.Filled.Add,
                        tint = Color.White,
                        contentDescription = "Add"
                    )
                }
            }
        },

        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(91.dp),
                backgroundColor = Color.White,

                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        items.forEachIndexed { index, item ->
                            val selected = item.route == backStackEntry.value?.destination?.route
                            BottomNavigationItem(
                                selected = selected,
                                onClick = { onItemClick(item) },
                                selectedContentColor = MaterialTheme.colors.onBackground,
                                unselectedContentColor = MaterialTheme.colors.secondary,

                                icon = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.name
                                        )
                                        if (selected) {
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Icon(
                                                modifier = Modifier
                                                    .width(26.dp)
                                                    .height(2.dp),
                                                painter = painterResource(id = R.drawable.line),
                                                contentDescription = "line",
                                            )
                                        }
                                    }
                                },
                            )
                        }
                    }
                })
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.primary)
        )
        Navigation(navController = navController)
    }
}