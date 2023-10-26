package com.project.viewreview.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.viewreview.presentation.authentication.SignInScreen
import com.project.viewreview.presentation.authentication.SignUpScreen

@Composable
fun AuthNavigator() {

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

    NavHost(
        navController = navController,
        startDestination = Route.SignInScreen.route
    ) {

        composable(Route.SignInScreen.route) {
            SignInScreen(
                exitAuthentication = { /*TODO*/ },
                navigateToSignUp = {
                    navigateToScreen(
                        navController = navController,
                        route = Route.SignUpScreen.route
                    )
                },
            )
        }

        composable(Route.SignUpScreen.route) {
            SignUpScreen()
        }
    }
}

private fun navigateToScreen(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { currentRoute ->
            popUpTo(currentRoute) {
                saveState = true
//                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}