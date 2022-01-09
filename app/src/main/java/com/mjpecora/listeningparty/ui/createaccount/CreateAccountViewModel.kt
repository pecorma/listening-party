package com.mjpecora.listeningparty.ui.createaccount

import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.base.ViewModel
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

    val viewState = MutableStateFlow(CreateAccountViewState())

    fun createUser(email: String, password: String, userName: String) = viewModelScope.launch {
        viewState.emit(
            CreateAccountViewState(true, CreateAccountInput(email, password, userName))
        )
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .createAccountListeners(userName, email)

    }

    private fun Task<AuthResult>.createAccountListeners(userName: String, email: String) {
        this.addOnSuccessListener { auth ->
            auth.user?.uid?.let { uid ->
                val user = User(userName, email, uid)
                viewModelScope.launch {
                    remoteUserRepository.writeNewUser(user).writeNewUserListeners(user)
                }
            }
        }
            .addOnFailureListener {
                viewModelScope.launch {
                    when (it.message) {
                        FirebaseAuthErrors.INVALID_EMAIL.message ->
                            viewState.emit(
                                CreateAccountViewState(
                                    false,
                                    viewState.value.createAccount.copy(
                                        isEmailError = true,
                                    )
                                )
                            )
                    }
                }
            }
    }

    private fun Task<Void>.writeNewUserListeners(user: User) {
        this.addOnSuccessListener {
            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    userDao.insertUser(user)
                }
                navigate(Navigator.NavTarget.Route(Screen.Home.route))
            }
        }
        .addOnFailureListener {
            viewModelScope.launch {
                viewState.emit(
                    CreateAccountViewState(
                        false,
                        viewState.value.createAccount.copy(
                            isUserNameError = true
                        )
                    )
                )
            }
        }
    }

}

enum class FirebaseAuthErrors(val message: String) {
    INVALID_EMAIL("The email address is badly formatted.")
}

data class CreateAccountViewState(
    val isLoading: Boolean = false,
    val createAccount: CreateAccountInput = CreateAccountInput.idle()
)

data class CreateAccountInput(
    val email: String,
    val password: String,
    val userName: String,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isUserNameError: Boolean = false
) {
    companion object {
        fun idle() = CreateAccountInput("", "", "")
    }
}