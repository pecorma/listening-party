package com.mjpecora.listeningparty.di

import android.content.Context
import com.mjpecora.listeningparty.model.cache.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

}