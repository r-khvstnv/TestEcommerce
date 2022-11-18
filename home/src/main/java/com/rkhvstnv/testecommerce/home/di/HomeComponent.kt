package com.rkhvstnv.testecommerce.home.di

import androidx.lifecycle.ViewModel
import com.rkhvstnv.testecommerce.core.ViewModelFactoryModule
import com.rkhvstnv.testecommerce.core.annotations.FeatureScope
import com.rkhvstnv.testecommerce.core.annotations.ViewModelKey
import com.rkhvstnv.testecommerce.home.ui.HomeFragment
import com.rkhvstnv.testecommerce.home.ui.HomeViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@[FeatureScope Component(
    dependencies = [HomeComponentDeps::class],
    modules = [HomeModule::class]
)]
internal interface HomeComponent {
    @Component.Builder
    interface Builder{
        fun deps(homeComponentDeps: HomeComponentDeps): Builder
        fun build(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
}

@Module(includes = [ViewModelFactoryModule::class])
internal abstract class HomeModule{
    @[Binds IntoMap ViewModelKey(HomeViewModel::class)]
    abstract fun bindsHomeViewModule(homeViewModel: HomeViewModel): ViewModel
}