package com.example.componentsmetric.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.componentsmetric.domain.useCase.GetAllProductUseCase

class ViewModelFactory(private val counter: GetAllProductUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComponentsViewModel (counter) as T
    }
}