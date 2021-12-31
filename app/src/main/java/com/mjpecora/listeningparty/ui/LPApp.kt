/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mjpecora.listeningparty.ui.home.Home
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
            Login {
                appState.navigateToHome(backStackEntry)
            }
        }
    }
}