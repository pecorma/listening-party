package com.mjpecora.listeningparty.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mjpecora.listeningparty.ui.createaccount.CreateAccount
import com.mjpecora.listeningparty.ui.login.Login
import com.mjpecora.listeningparty.ui.login.Navigate

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
                when (it) {
                    Navigate.HOME -> appState.navigateToHome(backStackEntry)
                    Navigate.CREATE_ACCOUNT -> appState.navigateToCreateAccount(backStackEntry)
                }
            }
        }

        composable(Screen.CreateAccount.route) {
            CreateAccount(hiltViewModel())
        }
    }
}