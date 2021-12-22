package com.mjpecora.listeningparty.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.mjpecora.listeningparty.ListeningPartyApp
import com.mjpecora.listeningparty.ui.theme.LPTheme
import com.mjpecora.listeningparty.util.spotify
import com.mjpecora.listeningparty.util.tag
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.SpotifyAppRemote
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
        spotify<Unit>()
    }

}