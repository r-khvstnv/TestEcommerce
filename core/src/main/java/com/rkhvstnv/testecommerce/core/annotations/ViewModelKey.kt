package com.rkhvstnv.testecommerce.core.annotations

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


/**
 * [MapKey] for [ViewModel]s.
 *
 * Annotation is required for
 * [ViewModelFactory][com.rkhvstnv.testecommerce.core.ViewModelFactory] implementation.
 * */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)