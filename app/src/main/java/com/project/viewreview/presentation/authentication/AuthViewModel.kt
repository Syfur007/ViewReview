package com.project.viewreview.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.project.viewreview.domain.repository.AuthRepository
import com.project.viewreview.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = Channel<AuthState>()
    val authState = _authState.receiveAsFlow()

    private val _googleState = mutableStateOf(GoogleAuthState())
    val googleState: State<GoogleAuthState> = _googleState


    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        authRepository.signInWithGoogle(credential).collect { result ->
            when (result) {
                is DataState.Success -> {
                    _googleState.value = GoogleAuthState(success = result.data)
                }
                is DataState.Loading -> {
                    _googleState.value = GoogleAuthState(loading = true)
                }
                is DataState.Error -> {
                    _googleState.value = GoogleAuthState(error = result.message!!)
                }
            }
        }
    }


    fun signInUserWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.signInWithEmailAndPassword(email, password).collect {result ->
            when (result) {
                is DataState.Success -> {
                    _authState.send(AuthState(isSuccessful = result.data?.user?.email))
                }

                is DataState.Loading -> {
                    _authState.send(AuthState(isLoading = true))
                }

                is DataState.Error -> {
                    _authState.send(AuthState(isError = result.message))
                }
            }
        }
    }

    fun signUpUserWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.signUpWithEmailAndPassword(email, password).collect { result ->
            when (result) {
                is DataState.Success -> {
                    _authState.send(AuthState(isSuccessful = result.data?.user?.email))
                    signInUserWithEmailAndPassword(email, password)
                }

                is DataState.Loading -> {
                    _authState.send(AuthState(isLoading = true))
                }

                is DataState.Error -> {
                    _authState.send(AuthState(isError = result.message))
                }
            }
        }
    }
}
