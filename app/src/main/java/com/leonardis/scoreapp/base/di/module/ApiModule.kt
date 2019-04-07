package com.leonardis.scoreapp.base.di.module

import com.leonardis.scoreapp.utils.Endpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideEndpoints(retrofit: Retrofit): Endpoints = retrofit.create(Endpoints::class.java)
}