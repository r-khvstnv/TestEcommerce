package com.rkhvstnv.testecommerce.core_data.di

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetHotSalesUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetPhoneByIdUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class UseCaseModule {
    @[Provides Singleton]
    fun providesGetHotSalesUseCase(repository: Repository): GetHotSalesUseCase = GetHotSalesUseCase(repository)
    @[Provides Singleton]
    fun providesGetPhoneByIdUseCase(repository: Repository): GetPhoneByIdUseCase = GetPhoneByIdUseCase(repository)
}