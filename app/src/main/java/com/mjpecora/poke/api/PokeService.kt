/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mjpecora.poke.model.remote.Pokemon
import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.model.remote.PokemonResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeService {

    @GET("pokemon")
    suspend fun getPaginatedPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = PAGING_LIMIT
    ): PokemonResponse

    @GET
    suspend fun getPokemon(@Url url: String): PokemonDetail

    companion object {
        const val PAGING_LIMIT = 100

        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create(): PokeService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(logger).build()
            val contentType = "application/json".toMediaType()
            val json = Json{ ignoreUnknownKeys = true }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
                .create(PokeService::class.java)
        }
    }

}