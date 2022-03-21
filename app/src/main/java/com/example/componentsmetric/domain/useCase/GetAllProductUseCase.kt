package com.example.componentsmetric.domain.useCase

import com.example.componentsmetric.domain.dto.Product

interface GetAllProductUseCase {
    suspend fun getAllProduct():List<Product>
}