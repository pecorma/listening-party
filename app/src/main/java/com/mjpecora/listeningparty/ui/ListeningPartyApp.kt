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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mjpecora.listeningparty.ui.home.Home
import com.mjpecora.listeningparty.ui.pokeinfo.PokeInfo

@Composable
fun PokeApp(
    appState: ListeningPartyAppState = rememberPokeAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { backStackEntry ->
            Home(hiltViewModel()) {
                appState.navigateToPokemonInfo(it, backStackEntry)
            }
        }
        composable(
            Screen.PokeInfo.route
        ) { backStackEntry ->
            PokeInfo(
                backStackEntry.arguments?.getString("name") ?: "",
                hiltViewModel()
            )
        }
    }
}