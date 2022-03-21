package com.example.componentsmetric.domain.repository

import com.example.componentsmetric.domain.dto.Product

interface Repository {
    suspend fun getAllProduct():List<Product>
}