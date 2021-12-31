package com.mjpecora.listeningparty.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mjpecora.listeningparty.ui.login.Login

@Composable
fun LPApp(
    appState: ListeningPartyAppState = rememberLPAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) { backStackEntry ->
            Login(hiltViewModel()) {
                appState.navigateToHome(backStackEntry)
            }
        }
    }
}