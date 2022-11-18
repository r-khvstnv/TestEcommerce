package com.rkhvstnv.testecommerce.core

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * [ViewModelFactoryModule] __must be__ added in each required [@Module][dagger.Module]/[@Component][dagger.Component]
 * separately. It helps to limit visibility of each [ViewModel][androidx.lifecycle.ViewModel] per library module.
 * */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}