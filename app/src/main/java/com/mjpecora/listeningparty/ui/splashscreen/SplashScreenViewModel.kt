package com.mjpecora.listeningparty.ui.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.ui.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {

    private val shouldAuthenticate = MutableLiveData(true)

    fun authenticate() = viewModelScope.launch {
        delay(1500L)
        if (shouldAuthenticate.value == true) {
            auth.currentUser?.let {
                navigate(Navigator.NavTarget.Route(Screen.Home.route))
            } ?: run {
                navigate(Navigator.NavTarget.Route(Screen.Login.route))
            }
            shouldAuthenticate.value = false
        }

    }

}