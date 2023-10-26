package com.project.viewreview.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authClient: GoogleAuthClient
) : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()


    fun onSignInResult(result: SignInResult) {
        _signInState.update {
            it.copy(
                isSignInSuccessful = result.userData != null,
                signInError = result.errorMessage
            )
        }
    }


    fun onCreateAccountResult(result: SignInResult) {
        _signInState.update {
            it.copy(
                isSignInSuccessful = result.userData != null,
                signInError = result.errorMessage
            )
        }
    }


    fun onSignInWithEmailResult(result: SignInResult) {
        _signInState.update {
            it.copy(
                isSignInSuccessful = result.userData != null,
                signInError = result.errorMessage
            )
        }
    }

    fun onSignInClick(email: String, password: String) {
        viewModelScope.launch {
            val result = authClient.signInWithEmail(email, password)
            onSignInResult(result)
        }
    }

    fun onRegisterClick(email: String, password: String) {
        viewModelScope.launch {
            val result = authClient.createAccountWithEmail(email, password)
            onSignInResult(result)
        }
    }


    fun resetState() {
        _signInState.update { SignInState() }
    }

}
