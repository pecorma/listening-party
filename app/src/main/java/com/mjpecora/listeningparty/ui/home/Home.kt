package com.mjpecora.listeningparty.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Home(
    navigateToPokeInfo: (String) -> Unit
) {
    Surface(Modifier.fillMaxSize()) {
//        PokemonGrid(
//            pagingDataFlow = pagingDataFlow,
//            navigateToPokeInfo = navigateToPokeInfo
//        )
    }
}
//
//@Composable
//fun PokemonGrid(
//    pagingDataFlow: Flow<PagingData<Pokemon>>,
//    navigateToPokeInfo: (String) -> Unit
//) {
//    val items = pagingDataFlow.collectAsLazyPagingItems()
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(2),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(8.dp)
//    ) {
//        items(items.itemCount) { index ->
//            PokemonCard(items[index], LPColors.surface.toArgb(), navigateToPokeInfo)
//        }
//    }
//}
//
//@Composable
//fun PokemonCard(
//    pokemon: Pokemon?,
//    dominantColor: Int,
//    navigateToPokeInfo: (String) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .height(200.dp)
//            .fillMaxWidth()
//            .clickable { navigateToPokeInfo(pokemon?.name ?: "") },
//        shape = LPShapes.medium,
//        backgroundColor = Color(dominantColor),
//    ) {
//        Image(
//            painter = rememberImagePainter(data = pokemon?.officialArtworkUrl),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}