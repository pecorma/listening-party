package com.mjpecora.listeningparty.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.mjpecora.listeningparty.base.Navigator
import com.mjpecora.listeningparty.ui.theme.LPTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
            .signOut()
            .addOnSuccessListener {
                setContent {
                    LPTheme {
                        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                            LPApp(rememberLPAppState())
                        }
                    }
                }
            }
    }

    @Composable
    fun rememberLPAppState(
        navController: NavHostController = rememberAnimatedNavController(),
        context: Context = LocalContext.current
    ) = remember(navController, context) {
        ListeningPartyAppState(navController, navigator)
    }

}