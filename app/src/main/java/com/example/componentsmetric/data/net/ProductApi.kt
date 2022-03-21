package com.example.componentsmetric.data.net

import com.example.componentsmetric.data.dto.ProductResponse

interface ProductApi {
    suspend fun getAllProduct(): List<ProductResponse>

}