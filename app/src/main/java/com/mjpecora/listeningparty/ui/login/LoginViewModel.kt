package com.mjpecora.listeningparty.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.model.cache.User
import com.mjpecora.listeningparty.model.cache.UserDao
import com.mjpecora.listeningparty.repository.RemoteUserRepository
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
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao,
    private val userRemoteRepository: RemoteUserRepository
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
                viewModelScope.launch {
                    it.user?.uid?.let { uid ->
                        userRemoteRepository.getUser(uid)
                            .addOnSuccessListener {
                                val user = it.getValue(User::class.java)
                                if (user != null) {
                                    viewModelScope.launch {
                                        withContext(Dispatchers.IO) {
                                            userDao.insertUser(user)
                                        }
                                    }
                                }
                                navigate(Navigator.NavTarget.Route(Screen.Home.route))
                            }
                    }
                }
            }
    }
}

sealed class LoginViewState : ViewState {
    object Idle : LoginViewState()
    object Loading : LoginViewState()
    object Success : LoginViewState()
}