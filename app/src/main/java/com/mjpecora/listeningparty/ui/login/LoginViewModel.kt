package com.mjpecora.listeningparty.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.ui.Screen
import com.mjpecora.listeningparty.util.tag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    val viewState = MutableStateFlow<LoginViewState>(LoginViewState.Idle)

    fun signInWithAuthCredential(credential: AuthCredential) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    Firebase.auth.signInWithCredential(credential)
                } catch (e: Exception) {
                    Log.d(this@LoginViewModel.tag(), "failed firebase sign in.")
                }
            }
        }
    }

    fun signInWithEmailPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                updateViewState(LoginViewState.Success(Screen.Login.Destination.HOME))
            }
    }


    private fun updateViewState(viewState: LoginViewState) = viewModelScope.launch {
        this@LoginViewModel.viewState.emit(viewState)
    }

}

sealed class LoginViewState : ViewState {
    object Idle : LoginViewState()
    object Loading : LoginViewState()
    data class Success(val destination: Screen.Login.Destination) : LoginViewState()
}