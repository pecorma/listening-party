/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.api

import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.model.remote.PokemonResponse
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
    }

}