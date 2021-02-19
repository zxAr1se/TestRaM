package com.example.testram.di

import android.app.Application
import com.example.testram.di.application.AppComponent
import com.example.testram.di.application.ApplicationModule
import com.example.testram.di.application.DaggerAppComponent

class App : Application(){

    val component : AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}