package com.rkhvstnv.testecommerce.home.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetAllCategoriesUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetBestSellersUseCase
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetHotSalesUseCase
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
interface HomeComponentDeps {
    val getAllCategoriesUseCase: GetAllCategoriesUseCase
    val getHotSalesUseCase: GetHotSalesUseCase
    val getBestSellersUseCase: GetBestSellersUseCase
}
/**
 * Interface provides required instances from appComponent
 * */
interface HomeComponentDepsProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: HomeComponentDeps

    //Real existed instance will be provided from DepsStore
    companion object : HomeComponentDepsProvider by HomeComponentDepsStore
}
/**
 * Explicit Singleton which must be assigned in [TestEcommerceApp]
 * */
object HomeComponentDepsStore: HomeComponentDepsProvider {
    override var deps: HomeComponentDeps by Delegates.notNull()
}

/**
 * ViewModel stores all instances of Components.
 * This implementation simplifies Component lifecycle handling.
 * Note: We can't store instances in the original (related to Fragment) ViewModel,
 * due to it being created by ViewModelFactory which should be injected too.
 * */
internal class HomeComponentViewModule: ViewModel() {
    val homeComponent = DaggerHomeComponent.builder().deps(HomeComponentDepsProvider.deps).build()
}