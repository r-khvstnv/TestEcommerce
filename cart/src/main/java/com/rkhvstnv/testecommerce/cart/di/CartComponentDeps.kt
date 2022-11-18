package com.rkhvstnv.testecommerce.cart.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.rkhvstnv.testecommerce.core_data.domain.usecases.GetAllProductsInCartUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecases.UpdateCartUseCase
import kotlin.properties.Delegates

/**
 * NOTE: This approach is implemented within the Module Scope, since its separate parts
 * request the same dependencies. However, the method can be used for each component separately.
 * */

/**
 * Interface for implicit dependency on AppComponent
 * (due to feature-module doesn't know anything about app-module).
 * As declared properties listed all methods required for further injection
 * in the corresponding feature module.
 *
 * Note: Should be inherited in AppComponent
 * */
interface CartComponentDeps {
    val updateCartUseCase: UpdateCartUseCase
    val getAllProductsInCartUseCase: GetAllProductsInCartUseCase
}
/**
 * Interface provides required instances from appComponent
 * */
interface CartComponentDepsProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: CartComponentDeps

    //Real existed instance will be provided from DepsStore
    companion object : CartComponentDepsProvider by CartComponentDepsStore
}
/**
 * Explicit Singleton which must be assigned in [TestEcommerceApp][com.rkhvstnv.testecommerce.TestEcommerceApp]
 * */
object CartComponentDepsStore: CartComponentDepsProvider {
    override var deps: CartComponentDeps by Delegates.notNull()
}

/**
 * ViewModel stores all instances of Components.
 * This implementation simplifies Component lifecycle handling.
 * Note: We can't store instances in the original (related to Fragment) ViewModel,
 * due to it being created by ViewModelFactory which should be injected too.
 * */
internal class CartComponentViewModule: ViewModel(){
    val cartComponent = DaggerCartComponent.builder().deps(CartComponentDepsProvider.deps).build()
}