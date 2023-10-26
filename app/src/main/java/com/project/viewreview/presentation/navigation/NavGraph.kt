package com.project.viewreview.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.project.viewreview.presentation.authentication.SignInScreen
import com.project.viewreview.presentation.authentication.SignUpScreen
import com.project.viewreview.presentation.onboarding.OnBoardingScreen
import com.project.viewreview.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }


        navigation(
            route = Route.AuthNavigation.route,
            startDestination = Route.SignInScreen.route
        ) {
            composable(Route.SignInScreen.route) {
                SignInScreen()
            }

            composable(Route.SignUpScreen.route) {
                SignUpScreen()
            }

        }


        navigation(
            route = Route.MovieNavigation.route,
            startDestination = Route.MovieNavigatorScreen.route
        ) {
            composable(Route.MovieNavigatorScreen.route) {
                MovieNavigator()
            }
        }
    }
}