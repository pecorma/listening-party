package com.mjpecora.poke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.mjpecora.poke.databinding.ActivityMainBinding
import com.mjpecora.poke.model.remote.PokemonDetail
import com.mjpecora.poke.ui.adapter.PokemonListAdapter
import com.mjpecora.poke.ui.adapter.PokemonLoadStateAdapter
import com.mjpecora.poke.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(viewModel) {
            binding.bindState(this.pagingDataFlow)
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