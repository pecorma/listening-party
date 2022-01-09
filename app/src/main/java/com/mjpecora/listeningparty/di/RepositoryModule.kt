package com.mjpecora.listeningparty.di

import com.google.firebase.database.DatabaseReference
import com.mjpecora.listeningparty.repository.RemoteUserRepository
import com.mjpecora.listeningparty.repository.RemoteUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRemoteRepository(
        databaseReference: DatabaseReference
    ): RemoteUserRepository = RemoteUserRepositoryImpl(databaseReference)

}