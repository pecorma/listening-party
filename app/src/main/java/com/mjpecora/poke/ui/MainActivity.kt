package com.mjpecora.poke.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.google.accompanist.insets.ProvideWindowInsets
import com.mjpecora.poke.databinding.ActivityMainBinding
import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.ui.adapter.PokemonListAdapter
import com.mjpecora.poke.ui.adapter.PokemonLoadStateAdapter
import com.mjpecora.poke.ui.theme.PokeTheme
import com.mjpecora.poke.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PokeTheme {
                ProvideWindowInsets {

                }
            }
        }
    }

    private fun ActivityMainBinding.bindState(pagingDataFlow: Flow<PagingData<PokemonDetail>>) {
        val adapter = PokemonListAdapter()
        this.pokeListRv.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PokemonLoadStateAdapter { adapter.retry() },
            footer = PokemonLoadStateAdapter { adapter.retry() }
        )

        lifecycleScope.launch {
            pagingDataFlow.collectLatest(adapter::submitData)
        }
    }

}