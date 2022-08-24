package com.example.feature_login.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.feature_login.data.AuthDataSource
import com.example.feature_login.data.AuthDataSourceImpl
import com.example.feature_login.data.LoginRepositoryImpl
import com.example.feature_login.domain.LoginRepository
import com.example.feature_login.domain.interactor.LoginInteracts
import com.example.feature_login.domain.interactor.LoginInteractsImpl
import com.example.feature_login.presentation.LoginViewModel
import com.example.feature_login.presentation.RegistrationViewModel
import com.example.feature_login.presentation.validation.Validator
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun inject() = loadKoinModules

private val loadKoinModules by lazy { loadKoinModules(myModule) }

val myModule = module {
    single { provideSharedPref(androidApplication()) }
    single { provideSharedPrefEditor(androidApplication()) }
    single<AuthDataSource> { AuthDataSourceImpl(get(), get()) }
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<LoginInteracts> { LoginInteractsImpl(get()) }
    single { Validator() }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegistrationViewModel(get(), get()) }
}

private const val PREFERENCES_FILE_KEY = "com.example.settings_preferences"

private fun provideSharedPref(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

private fun provideSharedPrefEditor(app: Application): SharedPreferences.Editor =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE).edit()