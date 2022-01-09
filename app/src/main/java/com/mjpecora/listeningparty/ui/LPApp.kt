package com.mjpecora.listeningparty.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope.SlideDirection
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.ui.createaccount.CreateAccountScreen
import com.mjpecora.listeningparty.ui.home.Home
import com.mjpecora.listeningparty.ui.login.LoginScreen
import com.mjpecora.listeningparty.ui.profile.ProfileScreen
import com.mjpecora.listeningparty.ui.splashscreen.SplashScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun LPApp(appState: ListeningPartyAppState) {
    LaunchedEffect("navigation") {
        appState.navigator.sharedFlow.onEach {
            when (it) {
                is Navigator.NavTarget.Pop -> appState.navController.popBackStack()
                is Navigator.NavTarget.Route -> appState.navController.navigate(it.route)
            }
        }.launchIn(this)
    }

    AnimatedNavHost(
        navController = appState.navController,
        startDestination = Screen.Splash.route,
        exitTransition = { ExitTransition.None },
        popExitTransition = { ExitTransition.None },
        enterTransition = { EnterTransition.None },
        popEnterTransition = { EnterTransition.None }
    ) {
        composable(
            route = Screen.Splash.route,
            exitTransition = { fadeOut(tween(400)) }
        ) {
            DisableBack()
            SplashScreen(hiltViewModel())
        }

        rootComposable(route = Screen.Login.route) {
            DisableBack()
            LoginScreen(hiltViewModel())
        }

        screenComposable(route = Screen.CreateAccount.route) {
            CreateAccountScreen(hiltViewModel())
        }

        rootComposable(route = Screen.Home.route) {
            DisableBack()
            Home(hiltViewModel())
        }

        screenComposable(route = Screen.Profile.route) {
            ProfileScreen(hiltViewModel())
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
        enterTransition = { fadeIn(tween(400))  },
        exitTransition = { slideOutOfContainer(SlideDirection.Left, tween(400)) },
        popEnterTransition = { slideIntoContainer(SlideDirection.Right, tween(400)) },
        popExitTransition = { ExitTransition.None },
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
        popEnterTransition = { slideIntoContainer(SlideDirection.Left, tween(400)) },
        popExitTransition = { slideOutOfContainer(SlideDirection.Right, tween(400)) },
        content = content
    )
}