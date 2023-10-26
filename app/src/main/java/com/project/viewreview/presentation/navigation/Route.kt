package com.project.viewreview.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object AppStartNavigation: Route("appStartNavigation")

    object OnBoardingScreen : Route("onBoardingScreen")


    object AuthNavigation : Route("authNavigation")

    object SignInScreen : Route("signInScreen")
    object SignUpScreen : Route("signUpScreen")


    object MovieNavigation: Route("movieNavigation")

    object HomeScreen : Route("homeScreen")
    object SearchScreen : Route("searchScreen")
    object BookmarkScreen : Route("bookmarkScreen")
    object MovieDetailScreen : Route("movieDetailScreen")
    object MovieNavigatorScreen: Route("movieNavigator")
}
