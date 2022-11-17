package com.rkhvstnv.testecommerce.di

import android.app.Application
import com.rkhvstnv.testecommerce.MainActivity
import com.rkhvstnv.testecommerce.cart.di.CartComponentDeps
import com.rkhvstnv.testecommerce.core_data.domain.usecase.AddToCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetAllProductsInCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetPhoneByIdUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.UpdateCartUseCase
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

    fun inject(mainActivity: MainActivity)

    override val getPhoneByIdUseCase: GetPhoneByIdUseCase
    override val addToCartUseCase: AddToCartUseCase
    override val getAllProductsInCartUseCase: GetAllProductsInCartUseCase
    override val updateCartUseCase: UpdateCartUseCase
}