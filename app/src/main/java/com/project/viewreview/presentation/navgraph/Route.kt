package com.project.viewreview.presentation.navgraph

sealed class Route(
    val route: String
) {

    object OnBoardingScreen : Route("onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object SearchScreen : Route("searchScreen")
    object BookmarkScreen : Route("bookmarkScreen")
    object MovieDetailScreen : Route("movieDetailScreen")
    object AppStartNavigation: Route("appStartNavigation")
    object MovieNavigation: Route("movieNavigation")
    object MovieNavigatorScreen: Route("movieNavigator")
}
