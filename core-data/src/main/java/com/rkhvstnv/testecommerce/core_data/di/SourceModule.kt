package com.rkhvstnv.testecommerce.core_data.di

import android.app.Application
import com.rkhvstnv.testecommerce.core_data.data.database.MockDatabase
import com.rkhvstnv.testecommerce.core_data.data.network.MockyService
import com.rkhvstnv.testecommerce.core_data.data.source.LocalSource
import com.rkhvstnv.testecommerce.core_data.data.source.RemoteSource
import com.rkhvstnv.testecommerce.core_data.data.utils.NetworkConst
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class SourceModule {
    @[Provides Singleton]
    fun providesRetrofitBuilder(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(NetworkConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @[Provides Singleton]
    fun providesMockyService(retrofit: Retrofit): MockyService = retrofit.create(MockyService::class.java)

    @[Provides Singleton]
    fun providesRemoteSource(mockyService: MockyService): RemoteSource = RemoteSource(service = mockyService)

    @[Provides Singleton]
    fun providesMockDatabase(application: Application): MockDatabase = MockDatabase(context = application.applicationContext)

    @[Provides Singleton]
    fun providesLocalSource(mockDatabase: MockDatabase): LocalSource = LocalSource(mockDatabase)
}