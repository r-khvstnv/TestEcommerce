package com.rkhvstnv.testecommerce.details.di

import androidx.lifecycle.ViewModel
import com.rkhvstnv.testecommerce.core.ViewModelFactoryModule
import com.rkhvstnv.testecommerce.core.annotations.FeatureScope
import com.rkhvstnv.testecommerce.core.annotations.ViewModelKey
import com.rkhvstnv.testecommerce.details.ui.DetailsFragment
import com.rkhvstnv.testecommerce.details.ui.DetailsViewModel
import com.rkhvstnv.testecommerce.details.ui.ShopFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@[FeatureScope Component(
    dependencies = [DetailsComponentDeps::class],
    modules = [DetailsModule::class]
)]
internal interface DetailsComponent {
    @Component.Builder
    interface Builder{
        fun deps(detailsComponentDeps: DetailsComponentDeps): Builder
        fun build(): DetailsComponent
    }

    fun inject(detailsFragment: DetailsFragment)
    fun inject(shopFragment: ShopFragment)
}

@Module(includes = [ViewModelFactoryModule::class])
internal abstract class DetailsModule {
    @[Binds IntoMap ViewModelKey(DetailsViewModel::class)]
    abstract fun bindsDetailsViewModel(cartViewModel: DetailsViewModel): ViewModel
}