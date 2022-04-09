package com.example.feature_components.di

import com.example.feature_components.domain.interactor.Interactor
import com.example.feature_components.domain.interactor.InteractorImpl
import org.koin.dsl.module

val domain = module {
    factory<Interactor> { InteractorImpl(get()) }
}