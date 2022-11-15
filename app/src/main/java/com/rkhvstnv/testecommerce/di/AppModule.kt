package com.rkhvstnv.testecommerce.di

import com.rkhvstnv.testecommerce.core_data.di.UseCaseModule
import dagger.Module

@Module(includes = [UseCaseModule::class])
object AppModule