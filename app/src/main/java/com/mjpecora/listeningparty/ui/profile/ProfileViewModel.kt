package com.mjpecora.listeningparty.ui.profile

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.base.ViewModel
import com.mjpecora.listeningparty.ui.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUi: AuthUI
) : ViewModel() {

    fun signOut(context: Context) {
        authUi.signOut(context)
            .addOnSuccessListener {
                navigate(Navigator.NavTarget.Route(Screen.Login.route))
            }
    }

}