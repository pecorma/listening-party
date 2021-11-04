/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.ui.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mjpecora.poke.api.PokeService
import com.mjpecora.poke.api.PokeService.Companion.PAGING_LIMIT
import com.mjpecora.poke.model.remote.PokemonDetail
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val service: PokeService
): PagingSource<Int, PokemonDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDetail> {
        val nextPage = params.key ?: 1
        val response = service.getPaginatedPokemon(PAGING_LIMIT * (nextPage - 1)).list.map {
            fetchPokemonDetailAsync(it.url)
        }.awaitAll()
        return LoadResult.Page(
            data = response,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = nextPage.plus(1)
        )
    }

    private suspend fun fetchPokemonDetailAsync(url: String): Deferred<PokemonDetail> {
        return supervisorScope {
            this.async {
                service.getPokemon(url)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDetail>): Int? {
        TODO("Not yet implemented")
    }

}