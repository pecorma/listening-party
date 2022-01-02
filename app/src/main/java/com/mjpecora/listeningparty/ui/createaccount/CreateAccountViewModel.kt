package com.mjpecora.listeningparty.ui.createaccount

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.model.cache.User
import com.mjpecora.listeningparty.model.cache.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userDao: UserDao
) : ViewModel() {

    val viewState = MutableStateFlow<CreateAccountViewState>(CreateAccountViewState.Idle)

    fun createUser(email: String, password: String, userName: String) = viewModelScope.launch {
        viewState.emit(CreateAccountViewState.Loading)
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                viewModelScope.launch {
                    if (it.isSuccessful) {
                        withContext(Dispatchers.IO) {
                            userDao.insertUser(User(userName = userName))
                        }
                        viewState.emit(CreateAccountViewState.Success)
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