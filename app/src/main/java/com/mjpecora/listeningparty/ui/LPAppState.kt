package com.mjpecora.listeningparty.ui

import androidx.navigation.NavHostController
import com.mjpecora.listeningparty.base.Navigator

sealed class Screen(val route: String) {
    object Login : Screen("login") {
        enum class Destination { HOME, CREATE_ACCOUNT }
    }
    object Home : Screen("home")
    object CreateAccount : Screen("createAccount") {
        enum class Destination { HOME, BACK }
    }
}

data class ListeningPartyAppState(val navController: NavHostController, val navigator: Navigator)