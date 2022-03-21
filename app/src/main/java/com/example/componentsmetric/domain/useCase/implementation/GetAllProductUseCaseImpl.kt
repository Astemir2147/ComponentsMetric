package com.example.componentsmetric.domain.useCase.implementation

import android.util.Log
import com.example.componentsmetric.domain.dto.Product
import com.example.componentsmetric.domain.repository.Repository
import com.example.componentsmetric.domain.useCase.GetAllProductUseCase

class GetAllProductUseCaseImpl(private val repository: Repository) : GetAllProductUseCase {
    override suspend fun getAllProduct(): List<Product> {
        Log.d("my_tag","getAllProductUseCase")
        return repository.getAllProduct()
    }

}