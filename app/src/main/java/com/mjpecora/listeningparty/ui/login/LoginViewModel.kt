package com.mjpecora.listeningparty.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mjpecora.listeningparty.base.ViewState
import com.mjpecora.listeningparty.util.tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.mjpecora.listeningparty.ui.login.LoginViewState.CreateAccount.AccountType

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
            Log.d(this@LoginViewModel.tag(), "${result?.user}")
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