package com.project.viewreview.presentation.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.viewreview.R
import com.project.viewreview.presentation.bookmark.BookmarkScreen
import com.project.viewreview.presentation.bookmark.BookmarkViewModel
import com.project.viewreview.presentation.details.DetailsScreen
import com.project.viewreview.presentation.details.DetailsViewModel
import com.project.viewreview.presentation.home.HomeScreen
import com.project.viewreview.presentation.home.HomeViewModel
import com.project.viewreview.presentation.menu.MenuScreen
import com.project.viewreview.presentation.navigation.components.BottomNavigationItem
import com.project.viewreview.presentation.navigation.components.MovieBottomNavigation
import com.project.viewreview.presentation.search.SearchScreen
import com.project.viewreview.presentation.search.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieNavigator(
    rootNavController: NavHostController
) {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmarks"),
            BottomNavigationItem(icon = R.drawable.ic_menu, text = "Menu")
        )
    }
    val context = LocalContext.current
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        Route.MenuScreen.route -> 3
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route ||
                backStackState?.destination?.route == Route.MenuScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            MovieBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.MenuScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
                val trendingMovies = viewModel.trendingMovies.collectAsLazyPagingItems()
                val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()
                val movies = listOf(trendingMovies, topRatedMovies, popularMovies)

                HomeScreen(
                    moviesList = movies,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { movieId ->
                        navigateToDetails(
                            navController = navController,
                            movieId = movieId
                        )
                    },
                    event = viewModel::onEvent,
                    state = viewModel.state.value
                )
            }

            composable(route = Route.MovieDetailScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                val scope = rememberCoroutineScope()
                val selectedMovie by viewModel.selectedMovie
                val movieCredits by viewModel.movieCredits
                val movieReviews by viewModel.reviews


                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("movieId")
                    ?.let { movieId ->
                        scope.launch {
                            viewModel.selectMovie(movieId)
                            viewModel.getMovieCredits(movieId)
                            viewModel.fetchReviews(movieId)
                        }
                        selectedMovie?.let { movie ->
                            movieCredits?.let { credits ->
                                DetailsScreen(
                                    movie = movie,
                                    movieCredits = credits,
                                    movieReviews = movieReviews,
                                    onEvent = viewModel::onEvent,
                                    navigateUp = { navController.navigateUp() },
                                    sideEffect = viewModel.sideEffect
                                )
                            }
                        }
                    }
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { movieId ->
                        navigateToDetails(
                            navController = navController,
                            movieId = movieId
                        )
                    }
                )
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { movieId ->
                        navigateToDetails(
                            navController = navController,
                            movieId = movieId
                        )
                    }
                )
            }

            composable(route = Route.MenuScreen.route) {
                MenuScreen(
                    onSignInClick = {
                        navigateToScreen(
                            navController = rootNavController,
                            route = Route.AuthNavigation.route
                        )
                    },
                    onMyReviewsClick = {
                        Toast.makeText(context, "Screen not Created Yet!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onChangePasswordClick = {
                        Toast.makeText(context, "Screen not Created Yet!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onAboutAppClick = {
                        Toast.makeText(context, "Screen not Created Yet!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onTermsAndConditionsClick = {
                        Toast.makeText(context, "Screen not Created Yet!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    onPrivacyPolicyClick = {
                        Toast.makeText(context, "Screen not Created Yet!", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, movieId: Int) {
    navController.currentBackStackEntry?.savedStateHandle?.set("movieId", movieId)
    navController.navigate(
        route = Route.MovieDetailScreen.route
    )
}

private fun navigateToScreen(navController: NavController, route: String) {
    navController.navigate(route) {
        // Don't pop up to the start destination of the current graph
        // when navigating to a different graph
        launchSingleTop = true
        restoreState = true
    }
}

