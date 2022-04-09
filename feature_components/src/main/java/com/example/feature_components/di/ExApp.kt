package com.example.feature_components.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Ex app который запускает зависимости Koin
 *
 * @author Zashaev Astemir on 2022-04-09
 */
class ExApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExApp)
            modules(dataModule, domain, presentation)
        }
    }
}