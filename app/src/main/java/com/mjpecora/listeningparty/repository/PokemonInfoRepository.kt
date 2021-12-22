package com.mjpecora.listeningparty.repository

import javax.inject.Inject

class PokemonInfoRepository @Inject constructor(private val pokeService: com.mjpecora.listeningparty.api.SpotifyService) {

    suspend fun fetchPokemonInfo(name: String) = pokeService.getPokemon(name)

}