package com.project.viewreview.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object OnBoardingScreen : Route("onBoardingScreen")

    object SignInScreen : Route("signInScreen")

    object SignUpScreen : Route("signUpScreen")

    object AuthNavigation : Route("authNavigation")

    object HomeScreen : Route("homeScreen")

    object SearchScreen : Route("searchScreen")

    object BookmarkScreen : Route("bookmarkScreen")

    object MovieDetailScreen : Route("movieDetailScreen")

    object AppStartNavigation: Route("appStartNavigation")

    object MovieNavigation: Route("movieNavigation")

    object MovieNavigatorScreen: Route("movieNavigator")
}
