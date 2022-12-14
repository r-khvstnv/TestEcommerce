package com.rkhvstnv.testecommerce.core_data.di

import com.rkhvstnv.testecommerce.core_data.domain.Repository
import com.rkhvstnv.testecommerce.core_data.domain.usecases.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class UseCaseModule {
    @[Provides Singleton]
    fun providesGetHotSalesUseCase(repository: Repository): GetHotSalesUseCase = GetHotSalesUseCase(repository)
    @[Provides Singleton]
    fun providesGetPhoneByIdUseCase(repository: Repository): GetPhoneByIdUseCase = GetPhoneByIdUseCase(repository)
    @[Provides Singleton]
    fun providesAddToCartUseCase(repository: Repository): AddToCartUseCase = AddToCartUseCase(repository)
    @[Provides Singleton]
    fun providesGetAllProductsInCartUseCase(repository: Repository): GetAllProductsInCartUseCase =
        GetAllProductsInCartUseCase(repository)
    @[Provides Singleton]
    fun providesUpdateCartUseCase(repository: Repository): UpdateCartUseCase = UpdateCartUseCase(repository)
    @[Provides Singleton]
    fun getAllCategoriesUseCase(repository: Repository): GetAllCategoriesUseCase = GetAllCategoriesUseCase(repository)
    @[Provides Singleton]
    fun getBestSellersUseCase(repository: Repository): GetBestSellersUseCase = GetBestSellersUseCase(repository)
}