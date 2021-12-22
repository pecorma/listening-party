/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mjpecora.poke.model.remote.Pokemon
import com.mjpecora.poke.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: PokemonRepository) : ViewModel() {

    fun getPokemon(): Flow<PagingData<Pokemon>> = repo.fetchPokemonList()

}