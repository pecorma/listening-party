/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.poke.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mjpecora.poke.R
import com.mjpecora.poke.databinding.LayoutLoadingFooterBinding
import com.mjpecora.poke.databinding.LayoutPokemonSimpleItemBinding
import com.mjpecora.poke.model.remote.PokemonDetail

class PokemonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonLoadStateAdapter.LoadingFooterViewHolder>() {

    override fun onBindViewHolder(holder: LoadingFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingFooterViewHolder {
        return LoadingFooterViewHolder.create(parent, retry)
    }

    class LoadingFooterViewHolder(
        private val binding: LayoutLoadingFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): LoadingFooterViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_loading_footer, parent, false)
                val binding = LayoutLoadingFooterBinding.bind(view)
                return LoadingFooterViewHolder(binding, retry)
            }
        }

    }

}

class PokemonListAdapter :
    PagingDataAdapter<PokemonDetail, PokemonListAdapter.PokemonDetailViewHolder>(comparator) {

    override fun onBindViewHolder(holder: PokemonDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonDetailViewHolder {
        return PokemonDetailViewHolder.create(parent)
    }

    class PokemonDetailViewHolder(
        private val binding: LayoutPokemonSimpleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(detail: PokemonDetail?) {
            binding.pokemonNameTv.text = detail?.name ?: "Probably pikachu, amirite?"
            Glide.with(binding.pokemonImageIv)
                .load(detail?.sprites?.otherArtwork?.officialArtwork?.frontDefaultUrl)
                .placeholder(R.drawable.ic_poke_ball)
                .fitCenter()
                .into(binding.pokemonImageIv)
        }

        companion object {
            fun create(parent: ViewGroup): PokemonDetailViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_pokemon_simple_item, parent, false)
                val binding = LayoutPokemonSimpleItemBinding.bind(view)
                return PokemonDetailViewHolder(binding)
            }
        }

    }

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<PokemonDetail>() {
            override fun areContentsTheSame(oldItem: PokemonDetail, newItem: PokemonDetail) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PokemonDetail, newItem: PokemonDetail): Boolean =
                oldItem.name == newItem.name
        }
    }

}