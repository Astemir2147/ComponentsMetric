package com.example.feature_login.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.feature_login.data.AuthDataSourceImpl
import com.example.feature_login.data.LoginRepositoryImpl
import com.example.feature_login.domain.LoginRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single { provideSharedPref(androidApplication()) }
    single { provideSharedPrefEditor(androidApplication()) }
    // single { AuthDataSourceImpl() }
    // factory<LoginRepository> { LoginRepositoryImpl(get()) }

}

private const val PREFERENCES_FILE_KEY = "com.example.settings_preferences"

private fun provideSharedPref(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

private fun provideSharedPrefEditor(app: Application): SharedPreferences.Editor =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE).edit()