package com.mjpecora.listeningparty.ui.pokeinfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.repository.PokemonInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PokemonInfoViewState(
    val name: String = "",
    val url: String = "",
)

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val repository: PokemonInfoRepository,
    private val dispatcher: CoroutineDispatcher,
): ViewModel() {

    var uiState by mutableStateOf(PokemonInfoViewState())
        private set

    fun fetchPokemonInfo(name: String) {
        viewModelScope.launch {
            val pokemonDetail = fetchPokemonInfoAsync(name).await()
            uiState = PokemonInfoViewState(
                pokemonDetail.name,
                pokemonDetail.sprites.otherArtwork.officialArtwork.frontDefaultUrl
            )
        }
    }

    private suspend fun fetchPokemonInfoAsync(name: String): Deferred<PokemonDetail> {
        return viewModelScope.async(dispatcher) {
            repository.fetchPokemonInfo(name)
        }
    }

}