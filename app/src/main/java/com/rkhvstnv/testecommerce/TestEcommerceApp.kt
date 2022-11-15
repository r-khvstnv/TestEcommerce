package com.rkhvstnv.testecommerce

import android.app.Application
import com.rkhvstnv.testecommerce.di.AppComponent
import com.rkhvstnv.testecommerce.di.DaggerAppComponent

class TestEcommerceApp: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }
    override fun onCreate() {
        super.onCreate()
    }
}