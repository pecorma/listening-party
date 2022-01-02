package com.mjpecora.listeningparty.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class Screen(val route: String) {
    object Login : Screen("login") {
        enum class Destination {
            HOME, CREATE_ACCOUNT
        }
    }
    object Home : Screen("home")
    object CreateAccount : Screen("createAccount") {
        enum class Destination {
            HOME, BACK
        }
    }
}

@Composable
fun rememberLPAppState(
    navController: NavHostController = rememberAnimatedNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    ListeningPartyAppState(navController)
}

class ListeningPartyAppState(val navController: NavHostController) {

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToHome(from: NavBackStackEntry) {
        if (from.isLifeCycleResumed()) {
            navController.navigate(Screen.Home.route)
        }
    }

    fun navigateToCreateAccount(from: NavBackStackEntry) {
        if (from.isLifeCycleResumed()) {
            navController.navigate(Screen.CreateAccount.route)
        }
    }

}

private fun NavBackStackEntry.isLifeCycleResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED