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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.ui.theme.PokeShapes
import com.mjpecora.poke.ui.theme.PokeTheme
import com.mjpecora.poke.ui.viewmodel.PokemonViewModel
import kotlinx.coroutines.flow.Flow

@Preview
@Composable
fun Home(
    viewModel: PokemonViewModel = viewModel()
) {
    val pagingDataFlow = viewModel.pagingDataFlow
    Surface(Modifier.fillMaxSize()) {
        PokemonGrid(pagingDataFlow = pagingDataFlow)
    }
}

@Composable
fun PokemonGrid(pagingDataFlow: Flow<PagingData<PokemonDetail>>) {
    val items = pagingDataFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(items.itemCount) { index ->
            PokemonCard(detail = items[index])
        }
    }
}

@Composable
fun PokemonCard(detail: PokemonDetail?) {
    Column(
        Modifier.height(200.dp).fillMaxWidth()) {
            Card(shape = PokeShapes.medium) {
                val imageUrl = detail?.sprites?.otherArtwork?.officialArtwork?.frontDefaultUrl
                Image(
                    painter = rememberImagePainter(data = imageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
    }
}

@Preview
@Composable
fun PreviewHome() {
    PokeTheme {
        Home()
    }
}