package com.rkhvstnv.testecommerce.di

import com.rkhvstnv.testecommerce.MainActivity
import com.rkhvstnv.testecommerce.cart.di.CartComponentDeps
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetPhoneByIdUseCase
import com.rkhvstnv.testecommerce.details.di.DetailsComponentDeps
import com.rkhvstnv.testecommerce.home.di.HomeComponentDeps
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent:
    CartComponentDeps,
    DetailsComponentDeps,
    HomeComponentDeps
{
    fun inject(mainActivity: MainActivity)

    override val getPhoneByIdUseCase: GetPhoneByIdUseCase
}