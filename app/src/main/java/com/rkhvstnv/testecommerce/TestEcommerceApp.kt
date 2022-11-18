package com.rkhvstnv.testecommerce

import android.app.Application
import com.rkhvstnv.testecommerce.cart.di.CartComponentDepsStore
import com.rkhvstnv.testecommerce.details.di.DetailsComponentDepsStore
import com.rkhvstnv.testecommerce.di.AppComponent
import com.rkhvstnv.testecommerce.di.DaggerAppComponent
import com.rkhvstnv.testecommerce.home.di.HomeComponentDepsStore

class TestEcommerceApp: Application() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
    override fun onCreate() {
        super.onCreate()

        appComponent.let {
            CartComponentDepsStore.deps = it
            DetailsComponentDepsStore.deps = it
            HomeComponentDepsStore.deps = it
        }
    }
}