package com.mjpecora.listeningparty.ui.profile

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.firebase.ui.auth.AuthUI
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.model.cache.User
import com.mjpecora.listeningparty.model.cache.UserDao
import com.mjpecora.listeningparty.ui.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUi: AuthUI,
    private val userDao: UserDao
) : ViewModel() {

    val userStateFlow = MutableStateFlow<User?>(null)

    init {
        getUser()
    }

    fun signOut(context: Context) {
        authUi.signOut(context)
            .addOnSuccessListener {
                navigate(Navigator.NavTarget.Route(Screen.Login.route))
            }
    }

    private fun getUser() = viewModelScope.launch {
        userDao.getUser().collectLatest {
            userStateFlow.emit(it)
        }
    }

}