package com.mjpecora.listeningparty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.mjpecora.listeningparty.ui.theme.LPTheme
import com.mjpecora.listeningparty.util.spotify
import com.spotify.android.appremote.api.ConnectionParams
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var connectionParams: ConnectionParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LPTheme {
                ProvideWindowInsets {
                    LPApp()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        spotify()
    }

}