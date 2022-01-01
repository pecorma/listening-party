package com.mjpecora.listeningparty.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.ui.login.LoginViewState.CreateAccount.AccountType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    val viewState = MutableStateFlow<LoginViewState>(LoginViewState.Idle)

    fun signInWithAuthCredential(credential: AuthCredential) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    Firebase.auth.signInWithCredential(credential).result
                } catch (e: Exception) {
                    viewState.emit(LoginViewState.CreateAccount(AccountType.GOOGLE))
                    null
                }
            }
        }
    }

}

sealed class LoginViewState : ViewState {
    object Idle : LoginViewState()
    object Loading : LoginViewState()

    data class CreateAccount(val type: AccountType) : LoginViewState() {

        enum class AccountType { GOOGLE }

    }
}

enum class Navigate {
    HOME, CREATE_ACCOUNT
}