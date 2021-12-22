/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mjpecora.listeningparty.api.SpotifyService.Companion.PAGING_LIMIT
import com.mjpecora.listeningparty.model.cache.PokemonDao
import com.mjpecora.listeningparty.model.remote.Pokemon
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val service: com.mjpecora.listeningparty.api.SpotifyService,
    private val pokemonDao: PokemonDao
): PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val nextPage = params.key ?: 1
        var pokemonList = pokemonDao.getPokemonList(params.key ?: 0)
        if (pokemonList.isEmpty()) {
            pokemonList = service.getPaginatedPokemon(PAGING_LIMIT * (nextPage - 1)).list
                .onEach {
                    it.page = (params.key ?: 1) - 1
                }
            pokemonDao.insertPokemonList(pokemonList)
        }
        return LoadResult.Page(
            data = pokemonList,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = nextPage.plus(1)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int = 0

}