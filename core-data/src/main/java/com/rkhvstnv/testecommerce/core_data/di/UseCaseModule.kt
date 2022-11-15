package com.rkhvstnv.testecommerce.core_data.di

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetHotSalesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class UseCaseModule {
    @[Provides Singleton]
    fun providesGetHotSalesUseCase(repository: Repository): GetHotSalesUseCase = GetHotSalesUseCase(repository)
}