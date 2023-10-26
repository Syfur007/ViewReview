package com.project.viewreview.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.project.viewreview.domain.repository.AuthRepository
import com.project.viewreview.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {


    override fun signInWithGoogle(
        credential: AuthCredential
    ): Flow<DataState<AuthResult>> {
        return flow {
            emit(DataState.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(DataState.Success(result))
        }.catch {
            emit(DataState.Error(it.message.toString()))
        }
    }


    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<DataState<AuthResult>> {
        return flow {
            emit(DataState.Loading())
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(DataState.Success(authResult))
        }.catch {
            emit(DataState.Error(it.message.toString()))
        }
    }


    override fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<DataState<AuthResult>> {
        return flow {
            emit(DataState.Loading())
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(DataState.Success(authResult))
        }.catch {
            emit(DataState.Error(it.message.toString()))
        }
    }
}