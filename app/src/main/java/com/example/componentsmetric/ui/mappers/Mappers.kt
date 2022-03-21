package com.example.componentsmetric.ui.mappers

import com.example.componentsmetric.domain.dto.Product
import com.example.componentsmetric.ui.dto.ComponentListItem

fun Product.productToComponentListItem() = ComponentListItem(id, productName, countOfProduct)