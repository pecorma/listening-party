/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mjpecora.poke.model.remote.Pokemon
import com.mjpecora.poke.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repo: PokemonRepository) : ViewModel() {

    val accept: (UiAction) -> Unit
    val pagingDataFlow: Flow<PagingData<Pokemon>>

    init {
        val actionStateFlow = MutableSharedFlow<UiAction>()
        val fetch = actionStateFlow
            .filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1)
            .onStart {
                emit(UiAction.Search)
            }

        pagingDataFlow = fetch
            .flatMapLatest {
                repo.fetchPokemonList()
            }
            .cachedIn(viewModelScope)

        accept = { action ->
            viewModelScope.launch { actionStateFlow.emit(action) }
        }
    }

}

sealed class UiAction {
    object Search : UiAction()
    object Scroll : UiAction()
}

object UiState