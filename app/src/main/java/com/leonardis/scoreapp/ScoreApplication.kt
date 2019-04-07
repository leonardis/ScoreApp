package com.leonardis.scoreapp

import android.app.Application
import com.leonardis.scoreapp.base.di.component.AppComponent
import com.leonardis.scoreapp.base.di.component.DaggerAppComponent
import com.leonardis.scoreapp.base.di.module.AppModule

class ScoreApplication: Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}