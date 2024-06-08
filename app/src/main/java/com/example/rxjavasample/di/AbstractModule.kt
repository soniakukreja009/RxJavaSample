package com.example.rxjavasample.di

import com.example.rxjavasample.data.datasource.RemoteSource
import com.example.rxjavasample.data.datasource.RemoteSourceImpl
import com.example.rxjavasample.domain.Repository
import com.example.rxjavasample.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindRemoteSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource
}