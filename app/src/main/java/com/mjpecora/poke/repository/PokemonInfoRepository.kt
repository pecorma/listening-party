package com.mjpecora.poke.repository

import com.mjpecora.poke.api.PokeService
import javax.inject.Inject

class PokemonInfoRepository @Inject constructor(private val pokeService: PokeService) {

    suspend fun fetchPokemonInfo(name: String) = pokeService.getPokemon(name)

}