package com.mjpecora.listeningparty.util

import com.mjpecora.listeningparty.ListeningPartyApp
import com.spotify.android.appremote.api.SpotifyAppRemote

fun Any.tag(): String = this::class.java.simpleName

fun spotify(isDisconnect: Boolean = false, action: (SpotifyAppRemote?.() -> Unit)? = null) {
    with (ListeningPartyApp.app.spotifyAppRemote) {
        if (isDisconnect.not() && (this == null || this.isConnected.not())) {
            ListeningPartyApp.app.spotifyConnect(action)
        } else {
            action?.invoke(this)
        }
    }
}