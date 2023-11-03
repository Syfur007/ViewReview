package com.project.viewreview.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AuthNavigation.route,
            startDestination = Route.SignInScreen.route
        ) {
            composable(Route.SignInScreen.route) {
                AuthNavigator(rootNavController = navController)
            }

        }

        navigation(
            route = Route.MovieNavigation.route,
            startDestination = Route.MovieNavigatorScreen.route
        ) {
            composable(Route.MovieNavigatorScreen.route) {
                MovieNavigator(rootNavController = navController)
            }
        }
    }
}