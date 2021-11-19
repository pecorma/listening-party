package com.mjpecora.poke.api

import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.model.remote.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokeService {

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