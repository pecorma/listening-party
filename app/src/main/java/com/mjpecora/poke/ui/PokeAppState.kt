/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object PokeInfo: Screen("pokeinfo/{name}") {
        fun createRoute(name: String) = "pokeinfo/$name"
    }
}

@Composable
fun rememberPokeAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    PokeAppState(navController, context)
}

class PokeAppState(
    val navController: NavHostController,
    private val context: Context
) {

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToPokemonInfo(name: String, from: NavBackStackEntry) {
        if (from.isLifeCycleResumed()) {
            navController.navigate(Screen.PokeInfo.createRoute(name))
        }
    }

}

private fun NavBackStackEntry.isLifeCycleResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED