package com.mjpecora.listeningparty.base

import androidx.lifecycle.ViewModel
import com.mjpecora.listeningparty.base.Navigator.NavTarget
import javax.inject.Inject

open class ViewModel : ViewModel() {

    @Inject lateinit var navigator: Navigator

    fun navigate(navTarget: NavTarget) {
        if (::navigator.isInitialized) {
            navigator.navigateTo(navTarget)
        }
    }

}