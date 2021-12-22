package com.mjpecora.listeningparty.model.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mjpecora.poke.model.remote.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Pokemon>)

    @Query("SELECT * FROM Pokemon WHERE page = :page")
    suspend fun getPokemonList(page: Int): List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE page <= :page")
    suspend fun getAllPokemonList(page: Int): List<Pokemon>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun getPokemon(name: String): Pokemon
}