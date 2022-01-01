package com.mjpecora.listeningparty.ui

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.mjpecora.listeningparty.ui.createaccount.CreateAccount
import com.mjpecora.listeningparty.ui.login.Login
import com.mjpecora.listeningparty.ui.login.Navigate
import androidx.compose.animation.AnimatedContentScope.SlideDirection

@Composable
fun LPApp(
    appState: ListeningPartyAppState = rememberLPAppState()
) {
    AnimatedNavHost(
        navController = appState.navController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route,
            enterTransition = { slideIntoContainer(SlideDirection.Right, tween(400)) },
            exitTransition = { slideOutOfContainer(SlideDirection.Left, tween(400)) }
        ) { backStackEntry ->
            Login(hiltViewModel()) {
                when (it) {
                    Navigate.HOME -> appState.navigateToHome(backStackEntry)
                    Navigate.CREATE_ACCOUNT -> appState.navigateToCreateAccount(backStackEntry)
                }
            }
        }

        composable(
            route = Screen.CreateAccount.route,
            enterTransition = { slideIntoContainer(SlideDirection.Left, tween(400)) },
            exitTransition = { slideOutOfContainer(SlideDirection.Right, tween(400)) }
        ) {
            CreateAccount(hiltViewModel())
        }
    }
}