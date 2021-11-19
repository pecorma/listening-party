package com.mjpecora.poke.ui.pokeinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.mjpecora.poke.ui.theme.PokeShapes

@Composable
fun PokeInfo(
    name: String,
    viewModel: PokemonInfoViewModel = viewModel(),
) {
    viewModel.fetchPokemonInfo(name)
    val uiState = viewModel.uiState
    Surface(Modifier.fillMaxSize()) {
        PlayerScreen(uiState = uiState)
    }
}

@Composable
private fun PlayerScreen(uiState: PokemonInfoViewState) {
    if (uiState.name.isNotBlank()) {
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                shape = PokeShapes.medium
            ) {
                Image(
                    painter = rememberImagePainter(data = uiState.url),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}