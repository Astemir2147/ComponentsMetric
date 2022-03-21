package com.example.componentsmetric

import android.app.Application
import com.example.componentsmetric.data.dataModule
import com.example.componentsmetric.domain.domain
import com.example.componentsmetric.ui.presentation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //androidLogger(Level.DEBUG)
            androidContext(this@DiApp)
            //androidFileProperties()
            //modules(allModules)
            modules(dataModule, domain, presentation)
        }
    }

}