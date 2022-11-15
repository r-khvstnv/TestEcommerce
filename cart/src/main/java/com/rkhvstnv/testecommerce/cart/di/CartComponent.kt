package com.rkhvstnv.testecommerce.cart.di

import androidx.lifecycle.ViewModel
import com.rkhvstnv.testecommerce.cart.CartFragment
import com.rkhvstnv.testecommerce.cart.CartViewModel
import com.rkhvstnv.testecommerce.core.ViewModelFactoryModule
import com.rkhvstnv.testecommerce.core.annotation.FeatureScope
import com.rkhvstnv.testecommerce.core.annotation.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@[FeatureScope Component(
    dependencies = [CartComponentDeps::class],
    modules = [CartModule::class]
)]
internal interface CartComponent {
    @Component.Builder
    interface Builder{
        fun deps(cartComponentDeps: CartComponentDeps): Builder
        fun build(): CartComponent
    }

    fun inject(cartFragment: CartFragment)
}

@Module(includes = [ViewModelFactoryModule::class])
internal abstract class CartModule{
    @[Binds IntoMap ViewModelKey(CartViewModel::class)]
    abstract fun bindsCartViewModel(cartViewModel: CartViewModel): ViewModel
}