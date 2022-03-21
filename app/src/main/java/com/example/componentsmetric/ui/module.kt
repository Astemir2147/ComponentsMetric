package com.example.componentsmetric.ui

import com.example.componentsmetric.ui.viewModel.ComponentsViewModel
import com.example.componentsmetric.ui.viewModel.ViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentation = module {

    // viewModel { ComponentsViewModel(get()) }
    factory { ViewModelFactory(get()) }


}
