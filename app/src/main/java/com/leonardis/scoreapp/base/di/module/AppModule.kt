package com.leonardis.scoreapp.base.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import com.leonardis.scoreapp.base.di.ActivityScope
import com.leonardis.scoreapp.utils.AppSchedulerProvider
import com.leonardis.scoreapp.viewmodels.FixtureViewModel
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesResources() = application.resources

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideFixtureViewModel(viewModel: FixtureViewModel): FixtureViewModel {
        return viewModel
    }

}