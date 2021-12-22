/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty

import android.app.Application
import android.util.Log
import com.mjpecora.listeningparty.util.tag
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ListeningPartyApp: Application() {

    @Inject
    lateinit var connectionParams: ConnectionParams

    var spotifyAppRemote: SpotifyAppRemote? = null
        private set

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    fun <T> spotifyConnect(action: (SpotifyAppRemote.() -> T)?) {
        SpotifyAppRemote.connect(this, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(spotifyAppRemote: SpotifyAppRemote?) {
                this@ListeningPartyApp.spotifyAppRemote = spotifyAppRemote
                if (spotifyAppRemote != null) {
                    action?.invoke(spotifyAppRemote)
                }
            }

            override fun onFailure(error: Throwable?) {
                Log.d(this@ListeningPartyApp.tag(), "Unable to connect to spotify.")
            }
        })
    }

    companion object {
        lateinit var app: ListeningPartyApp
            private set
    }

}