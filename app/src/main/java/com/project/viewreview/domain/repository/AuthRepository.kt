package com.project.viewreview.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.project.viewreview.util.DataState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signInWithGoogle(credential: AuthCredential): Flow<DataState<AuthResult>>

    fun signInWithEmailAndPassword(email: String, password: String): Flow<DataState<AuthResult>>

    fun signUpWithEmailAndPassword(email: String, password: String): Flow<DataState<AuthResult>>

}