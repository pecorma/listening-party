package com.mjpecora.listeningparty.di

import com.spotify.android.appremote.api.ConnectionParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CLIENT_ID = "0425167cfd5c4a23b7765f04468d3ce2"
    private const val CLIENT_REDIRECT_URI = "listening-party-spotify-login://callback"


    @Singleton
    @Provides
    fun provideConnectionParams(): ConnectionParams =
        ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(CLIENT_REDIRECT_URI)
            .showAuthView(true)
            .build()

}