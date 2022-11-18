package com.rkhvstnv.testecommerce.core_data.di

import com.rkhvstnv.testecommerce.core_data.data.RepositoryImpl
import com.rkhvstnv.testecommerce.core_data.data.sources.LocalSource
import com.rkhvstnv.testecommerce.core_data.data.sources.RemoteSource
import com.rkhvstnv.testecommerce.core_data.domain.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [SourceModule::class])
internal class DataModule {
    @[Provides Singleton]
    fun providesRepository(localSource: LocalSource, remoteSource: RemoteSource): Repository =
        RepositoryImpl(localSource, remoteSource)
}