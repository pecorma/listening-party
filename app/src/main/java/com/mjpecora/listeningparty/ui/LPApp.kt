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
import com.mjpecora.listeningparty.ui.createaccount.CreateAccountScreen
import com.mjpecora.listeningparty.ui.home.Home
import com.mjpecora.listeningparty.ui.login.LoginScreen

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
            LoginScreen(hiltViewModel()) {
                when (it) {
                    Screen.Login.Destination.HOME -> appState.navigateToHome(backStackEntry)
                    Screen.Login.Destination.CREATE_ACCOUNT-> appState.navigateToCreateAccount(backStackEntry)
                }
            }
        }

        screenComposable(route = Screen.CreateAccount.route) { backStackEntry ->
            CreateAccountScreen(hiltViewModel()) {
                when (it) {
                    Screen.CreateAccount.Destination.HOME ->
                        appState.navigateToHome(backStackEntry)

                    Screen.CreateAccount.Destination.BACK -> appState.navigateBack()
                }
            }
        }

        screenComposable(route = Screen.Home.route) {
            Home(hiltViewModel())
        }
    }
}

@Composable
fun DisableBack() = BackHandler(true) {}

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