package com.example.componentsmetric.data.converter

import com.example.componentsmetric.data.dto.ProductResponse
import com.example.componentsmetric.domain.dto.Product

class Converter {
    private fun ProductResponse.toProduct() = Product(id,productName, countOfProduct)

    fun toProductList(list: List<ProductResponse>): List<Product> {
        return list.map { it.toProduct() }
    }
    // fun toRun(run: RunEntity): Run {
    //     return Run(
    //         avgSpeedInKMH = run.avgSpeedInKMH,
    //         calories = run.calories,
    //         distanceInMeters = run.distanceInMeters,
    //         timeInMillis = run.timeInMillis,
    //         timestamp = run.timestamp,
    //         toDeleteFlag = run.toDeleteFlag,
    //         mapImage = run.mapImage
    //     )
    // }
}