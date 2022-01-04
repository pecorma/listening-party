package com.mjpecora.listeningparty.ui

import androidx.navigation.NavHostController
import com.mjpecora.listeningparty.base.Navigator

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Home : Screen("home")
    object CreateAccount : Screen("createAccount")
    object Profile : Screen("profile")
}

data class ListeningPartyAppState(val navController: NavHostController, val navigator: Navigator)