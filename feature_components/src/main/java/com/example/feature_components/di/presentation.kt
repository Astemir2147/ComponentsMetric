package com.example.feature_components.di

import com.example.feature_components.presentation.ComponentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentation = module {
    viewModel{ ComponentsViewModel(get()) }
}