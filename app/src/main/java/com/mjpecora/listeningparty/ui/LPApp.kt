package com.mjpecora.listeningparty.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope.SlideDirection
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.mjpecora.listeningparty.ui.createaccount.CreateAccount
import com.mjpecora.listeningparty.ui.login.Login
import com.mjpecora.listeningparty.ui.login.Navigate

@Composable
fun LPApp(
    appState: ListeningPartyAppState = rememberLPAppState(),
) {
    AnimatedNavHost(
        navController = appState.navController,
        startDestination = Screen.Login.route
    ) {
        rootComposable(route = Screen.Login.route) { backStackEntry ->
            DisableBack()
            Login(hiltViewModel()) {
                when (it) {
                    Navigate.HOME -> appState.navigateToHome(backStackEntry)
                    Navigate.CREATE_ACCOUNT -> appState.navigateToCreateAccount(backStackEntry)
                }
            }
        }

        screenComposable(route = Screen.CreateAccount.route) {
            CreateAccount(hiltViewModel()) {
                appState.navigateBack()
            }
        }
    }
}

@Composable
fun DisableBack() {
    BackHandler(true) {}
}

fun NavGraphBuilder.rootComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideIntoContainer(SlideDirection.Right, tween(400)) },
        exitTransition = { slideOutOfContainer(SlideDirection.Left, tween(400)) },
        content = content
    )
}

fun NavGraphBuilder.screenComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideIntoContainer(SlideDirection.Left, tween(400)) },
        exitTransition = { slideOutOfContainer(SlideDirection.Right, tween(400)) },
        content = content
    )
}