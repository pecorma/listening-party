package com.mjpecora.listeningparty.api

import com.mjpecora.listeningparty.model.remote.PokemonDetail
import com.mjpecora.listeningparty.model.remote.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("pokemon")
    suspend fun getPaginatedPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = PAGING_LIMIT
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonDetail

    companion object {
        const val PAGING_LIMIT = 100
    }

}