package com.example.feature_components.di

import com.example.feature_components.domain.interactor.ComponentsInteractor
import com.example.feature_components.domain.interactor.ComponentsInteractorImpl
import com.example.feature_components.domain.utils.networkUtil.NetworkDelegate
import com.example.feature_components.domain.utils.networkUtil.NetworkDelegateImpl
import org.koin.dsl.module

val domain = module {
    factory<ComponentsInteractor> { ComponentsInteractorImpl(get(), get()) }

    factory<NetworkDelegate> { NetworkDelegateImpl() }
}