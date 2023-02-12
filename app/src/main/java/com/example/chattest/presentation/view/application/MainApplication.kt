package com.example.chattest.presentation.view.application

import android.app.Application
import com.example.chattest.presentation.di.mobileModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(mobileModules)
        }
    }
}