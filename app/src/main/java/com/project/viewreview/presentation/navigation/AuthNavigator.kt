package com.project.viewreview.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.viewreview.presentation.authentication.SignInScreen
import com.project.viewreview.presentation.authentication.SignUpScreen

@Composable
fun AuthNavigator(
    rootNavController: NavHostController
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.SignInScreen.route
    ) {

        composable(Route.SignInScreen.route) {
            SignInScreen(
                exitAuthentication = {
                    navigateToScreen(
                        navController = rootNavController,
                        route = Route.MovieNavigatorScreen.route
                    )
                },
                navigateToSignUp = {
                    navigateToScreen(
                        navController = navController,
                        route = Route.SignUpScreen.route
                    )
                },
            )
        }

        composable(Route.SignUpScreen.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navigateToScreen(
                        navController = navController,
                        route = Route.SignInScreen.route
                    )
                },
                exitAuthentication = {
                    navigateToScreen(
                        navController = rootNavController,
                        route = Route.MovieNavigatorScreen.route
                    )
                },
            )
        }
    }
}

private fun navigateToScreen(navController: NavController, route: String) {
    navController.navigate(route) {
//        navController.graph.startDestinationRoute?.let { currentRoute ->
//            popUpTo(currentRoute) {
//                saveState = true
////                inclusive = true
//            }
//        }
        launchSingleTop = true
        restoreState = true
    }
}