package com.project.viewreview.presentation.navigation

sealed class Route(
    val route: String
) {
    object AuthNavigation : Route("authNavigation")

    object SignInScreen : Route("signInScreen")
    object SignUpScreen : Route("signUpScreen")


    object MovieNavigation: Route("movieNavigation")

    object HomeScreen : Route("homeScreen")
    object SearchScreen : Route("searchScreen")
    object BookmarkScreen : Route("bookmarkScreen")
    object MenuScreen : Route("menuScreen")
    object MovieDetailScreen : Route("movieDetailScreen")
    object MovieNavigatorScreen: Route("movieNavigator")
}
