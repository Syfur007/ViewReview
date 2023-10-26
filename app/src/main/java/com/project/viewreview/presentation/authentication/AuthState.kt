package com.project.viewreview.presentation.authentication

import com.google.firebase.auth.AuthResult

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccessful: String? = "",
    val isError: String? = ""
)

data class GoogleAuthState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String = ""
)
