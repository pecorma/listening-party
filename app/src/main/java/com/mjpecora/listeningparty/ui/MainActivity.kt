package com.mjpecora.listeningparty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.mjpecora.listeningparty.ui.theme.LPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            LPTheme {
                ProvideWindowInsets {
                    ListeningPartyApp()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }
}