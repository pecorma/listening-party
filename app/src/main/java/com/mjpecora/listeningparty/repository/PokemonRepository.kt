package com.mjpecora.listeningparty.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mjpecora.listeningparty.model.remote.Pokemon
import com.mjpecora.listeningparty.ui.home.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pagingSource: PokemonPagingSource
)  {

    fun fetchPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = com.mjpecora.listeningparty.api.SpotifyService.PAGING_LIMIT,
                enablePlaceholders = false,
                prefetchDistance = 50
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

}