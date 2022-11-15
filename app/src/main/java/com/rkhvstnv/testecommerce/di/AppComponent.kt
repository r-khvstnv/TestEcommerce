package com.rkhvstnv.testecommerce.di

import com.rkhvstnv.testecommerce.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}