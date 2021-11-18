/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mjpecora.poke.api.PokeService
import com.mjpecora.poke.model.remote.Pokemon
import com.mjpecora.poke.ui.datasource.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pagingSource: PokemonPagingSource
)  {

    fun fetchPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PokeService.PAGING_LIMIT,
                enablePlaceholders = false,
                prefetchDistance = 50
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

}