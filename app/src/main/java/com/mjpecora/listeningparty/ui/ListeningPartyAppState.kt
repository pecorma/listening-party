/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}

@Composable
fun rememberLPAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    ListeningPartyAppState(navController, context)
}

class ListeningPartyAppState(
    val navController: NavHostController,
    private val context: Context
) {

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToHome(from: NavBackStackEntry) {
        if (from.isLifeCycleResumed()) {
            navController.navigate(Screen.Home.route)
        }
    }

}

private fun NavBackStackEntry.isLifeCycleResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED