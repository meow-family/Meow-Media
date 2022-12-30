package com.octopus.socialnetwork.ui.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.octopus.socialnetwork.ui.screen.login.state.LoginViewModel
import com.octopus.socialnetwork.ui.screen.main.mainRoute
import com.octopus.socialnetwork.ui.screen.register.RegisterViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
    isLoggedOut: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedOut) Graph.AUTH else Graph.MAIN,
        route = Graph.ROOT
    ) {
        authNavigationGraph(
            navController = navController,
            loginViewModel = loginViewModel,
            registerViewModel = registerViewModel,
        )
        mainRoute(navController)
        detailsNavigationGraph(navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
    const val DETAILS = "details_graph"
}