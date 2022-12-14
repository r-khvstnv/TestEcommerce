package com.rkhvstnv.testecommerce.di

import android.app.Application
import com.rkhvstnv.testecommerce.cart.di.CartComponentDeps
import com.rkhvstnv.testecommerce.core_data.domain.usecases.*
import com.rkhvstnv.testecommerce.details.di.DetailsComponentDeps
import com.rkhvstnv.testecommerce.home.di.HomeComponentDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent:
    CartComponentDeps,
    DetailsComponentDeps,
    HomeComponentDeps
{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override val getPhoneByIdUseCase: GetPhoneByIdUseCase
    override val addToCartUseCase: AddToCartUseCase
    override val getAllProductsInCartUseCase: GetAllProductsInCartUseCase
    override val updateCartUseCase: UpdateCartUseCase
    override val getAllCategoriesUseCase: GetAllCategoriesUseCase
    override val getHotSalesUseCase: GetHotSalesUseCase
    override val getBestSellersUseCase: GetBestSellersUseCase
}