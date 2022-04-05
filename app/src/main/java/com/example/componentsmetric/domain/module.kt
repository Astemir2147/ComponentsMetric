package com.example.componentsmetric.domain

import com.example.componentsmetric.domain.useCase.GetAllProductUseCase
import com.example.componentsmetric.domain.useCase.implementation.GetAllProductUseCaseImpl
import org.koin.dsl.module

val domain = module {
    factory<GetAllProductUseCase> { GetAllProductUseCaseImpl(get()) }
}