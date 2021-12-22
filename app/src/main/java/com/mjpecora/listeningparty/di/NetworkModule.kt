/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mjpecora.listeningparty.model.cache.PokemonDao
import com.mjpecora.listeningparty.ui.home.PokemonPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    fun providePokeClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json{ ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun providePokeService(retrofit: Retrofit): com.mjpecora.listeningparty.api.SpotifyService {
        return retrofit.create(com.mjpecora.listeningparty.api.SpotifyService::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonPagingSource(
        service: com.mjpecora.listeningparty.api.SpotifyService,
        pokemonDao: PokemonDao
    ): PokemonPagingSource {
        return PokemonPagingSource(service, pokemonDao)
    }

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}