package com.rkhvstnv.testecommerce.details.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.rkhvstnv.testecommerce.core_data.domain.usecase.GetPhoneByIdUseCase
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
interface DetailsComponentDeps {
    val getPhoneByIdUseCase: GetPhoneByIdUseCase
}
/**
 * Interface provides required instances from appComponent
 * */
interface DetailsComponentDepsProvider{
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: DetailsComponentDeps

    //Real existed instance will be provided from DepsStore
    companion object : DetailsComponentDepsProvider by DetailsComponentDepsStore
}
/**
 * Explicit Singleton which must be assigned in [TestEcommerceApp]
 * */
object DetailsComponentDepsStore: DetailsComponentDepsProvider {
    override var deps: DetailsComponentDeps by Delegates.notNull()
}

/**
 * ViewModel stores all instances of Components.
 * This implementation simplifies Component lifecycle handling.
 * Note: We can't store instances in the original (related to Fragment) ViewModel,
 * due to it being created by ViewModelFactory which should be injected too.
 * */
internal class DetailsComponentViewModule: ViewModel(){
    val detailsComponent = DaggerDetailsComponent.builder().deps(DetailsComponentDepsProvider.deps).build()
}