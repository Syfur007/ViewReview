package com.project.viewreview.presentation.authentication


data class SignInResult(
    val userData: UserData?,
    val errorMessage: String?
)


data class UserData(
    val userId: String,
    val userName: String?,
    val email: String,
    val photoUrl: String?
)