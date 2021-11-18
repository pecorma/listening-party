package com.mjpecora.poke.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mjpecora.poke.model.remote.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}