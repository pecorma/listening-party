/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.mjpecora.poke.model.remote.Pokemon
import com.mjpecora.poke.ui.theme.PokeColors
import com.mjpecora.poke.ui.theme.PokeShapes
import com.mjpecora.poke.ui.theme.PokeTheme
import com.mjpecora.poke.ui.viewmodel.PokemonViewModel
import kotlinx.coroutines.flow.Flow

@Preview
@Composable
fun Home(
    viewModel: PokemonViewModel = viewModel()
) {
    val pagingDataFlow = viewModel.getPokemon()
    Surface(Modifier.fillMaxSize()) {
        PokemonGrid(pagingDataFlow = pagingDataFlow)
    }
}

@Composable
fun PokemonGrid(pagingDataFlow: Flow<PagingData<Pokemon>>) {
    val items = pagingDataFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items.itemCount) { index ->
            PokemonCard(items[index], PokeColors.surface.toArgb())
        }
    }
}

@Composable
fun PokemonCard(pokemon: Pokemon?, dominantColor: Int) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        shape = PokeShapes.medium,
        backgroundColor = Color(dominantColor)
    ) {
        Image(
            painter = rememberImagePainter(data = pokemon?.officialArtworkUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun PreviewHome() {
    PokeTheme {
        Home()
    }
}