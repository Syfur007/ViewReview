package com.project.viewreview.presentation.mainActivity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.project.viewreview.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _startDestination = mutableStateOf(Route.AuthNavigation.route)
    val startDestination: State<String> = _startDestination

    private val currentUser = FirebaseAuth.getInstance().currentUser

    init {
        if (currentUser != null) {
            _startDestination.value = Route.MovieNavigation.route
        } else {
            _startDestination.value = Route.AuthNavigation.route
        }
    }


}