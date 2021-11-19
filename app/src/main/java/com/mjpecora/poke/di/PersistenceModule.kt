package com.mjpecora.poke.di

import android.app.Application
import androidx.room.Room
import com.mjpecora.poke.model.cache.AppDatabase
import com.mjpecora.poke.model.cache.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "Pokedex.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase): PokemonDao = appDatabase.pokemonDao()


}