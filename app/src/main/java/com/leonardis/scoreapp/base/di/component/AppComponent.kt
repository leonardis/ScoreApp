package com.leonardis.scoreapp.base.di.component

import android.app.Application
import android.content.res.Resources
import com.google.gson.Gson
import com.leonardis.scoreapp.base.di.module.ApiModule
import com.leonardis.scoreapp.base.di.module.AppModule
import com.leonardis.scoreapp.base.di.module.OkHttpModule
import com.leonardis.scoreapp.base.di.module.RetrofitModule
import com.leonardis.scoreapp.utils.AppSchedulerProvider
import com.leonardis.scoreapp.utils.Endpoints
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, OkHttpModule::class))
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun resources(): Resources
    fun retrofit(): Retrofit
    fun endpoints(): Endpoints
    fun cache(): Cache
    fun client(): OkHttpClient
    fun loggingInterceptor(): HttpLoggingInterceptor
    fun compositeDisposable(): CompositeDisposable
    fun schedulerProvider(): AppSchedulerProvider
}