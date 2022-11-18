package com.rkhvstnv.testecommerce.core_data.di

import android.app.Application
import com.rkhvstnv.testecommerce.core_data.data.database.MockDatabase
import com.rkhvstnv.testecommerce.core_data.data.network.MockyService
import com.rkhvstnv.testecommerce.core_data.data.network.RetrofitContainer
import com.rkhvstnv.testecommerce.core_data.data.sources.LocalSource
import com.rkhvstnv.testecommerce.core_data.data.sources.RemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class SourceModule {
    @[Provides Singleton]
    fun providesRetrofitBuilder(): RetrofitContainer = RetrofitContainer

    @[Provides Singleton]
    fun providesMockyService(retrofit: RetrofitContainer): MockyService = retrofit.get().create(MockyService::class.java)

    @[Provides Singleton]
    fun providesRemoteSource(mockyService: MockyService): RemoteSource = RemoteSource(service = mockyService)

    @[Provides Singleton]
    fun providesMockDatabase(application: Application): MockDatabase = MockDatabase(context = application.applicationContext)

    @[Provides Singleton]
    fun providesLocalSource(mockDatabase: MockDatabase): LocalSource = LocalSource(mockDatabase)
}