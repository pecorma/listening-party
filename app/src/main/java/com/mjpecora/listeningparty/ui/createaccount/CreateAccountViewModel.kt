package com.mjpecora.listeningparty.ui.createaccount

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.model.cache.User
import com.mjpecora.listeningparty.model.cache.UserDao
import com.mjpecora.listeningparty.repository.RemoteUserRepository
import com.mjpecora.listeningparty.ui.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao,
    private val remoteUserRepository: RemoteUserRepository
) : ViewModel() {

    val viewState = MutableStateFlow<CreateAccountViewState>(CreateAccountViewState.Idle)

    fun createUser(email: String, password: String, userName: String) = viewModelScope.launch {
        viewState.emit(CreateAccountViewState.Loading)
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                it.user?.uid?.let {
                    val user = User(userName, email, it)
                    viewModelScope.launch {
                        remoteUserRepository.writeNewUser(user)
                            .addOnSuccessListener {
                                viewModelScope.launch {
                                    withContext(Dispatchers.Main) {
                                        userDao.insertUser(user)
                                    }
                                    navigate(Navigator.NavTarget.Route(Screen.Home.route))
                                }
                            }
                    }
                }
            }
    }

}

sealed class CreateAccountViewState : ViewState {
    object Loading : CreateAccountViewState()
    object Idle : CreateAccountViewState()
    object Success : CreateAccountViewState()
}