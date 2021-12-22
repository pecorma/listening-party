package com.mjpecora.listeningparty.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.mjpecora.listeningparty.model.remote.Pokemon
import com.mjpecora.listeningparty.ui.theme.LPColors
import com.mjpecora.listeningparty.ui.theme.LPShapes
import kotlinx.coroutines.flow.Flow

@Composable
fun Home(
    viewModel: PokemonViewModel = viewModel(),
    navigateToPokeInfo: (String) -> Unit
) {
    val pagingDataFlow = viewModel.getPokemon()
    Surface(Modifier.fillMaxSize()) {
        PokemonGrid(
            pagingDataFlow = pagingDataFlow,
            navigateToPokeInfo = navigateToPokeInfo
        )
    }
}

@Composable
fun PokemonGrid(
    pagingDataFlow: Flow<PagingData<Pokemon>>,
    navigateToPokeInfo: (String) -> Unit
) {
    val items = pagingDataFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items.itemCount) { index ->
            PokemonCard(items[index], LPColors.surface.toArgb(), navigateToPokeInfo)
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: Pokemon?,
    dominantColor: Int,
    navigateToPokeInfo: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clickable { navigateToPokeInfo(pokemon?.name ?: "") },
        shape = LPShapes.medium,
        backgroundColor = Color(dominantColor),
    ) {
        Image(
            painter = rememberImagePainter(data = pokemon?.officialArtworkUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}