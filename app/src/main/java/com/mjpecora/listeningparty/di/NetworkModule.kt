/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.di

import android.content.Context
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
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



//    @Singleton
//    @Provides
//    fun provideRetrofit(client: OkHttpClient): Retrofit {
//        val contentType = "application/json".toMediaType()
//        val json = Json{ ignoreUnknownKeys = true }
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun providePokeService(retrofit: Retrofit): com.mjpecora.listeningparty.api.SpotifyService {
//        return retrofit.create(com.mjpecora.listeningparty.api.SpotifyService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun providePokemonPagingSource(
//        service: com.mjpecora.listeningparty.api.SpotifyService,
//        pokemonDao: PokemonDao
//    ): PokemonPagingSource {
//        return PokemonPagingSource(service, pokemonDao)
//    }

}