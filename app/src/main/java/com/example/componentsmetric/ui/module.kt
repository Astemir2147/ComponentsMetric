package com.example.componentsmetric.ui

import com.example.componentsmetric.ui.viewModel.ComponentsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val presentation = module {
    viewModel { ComponentsViewModel(get()) }
}
