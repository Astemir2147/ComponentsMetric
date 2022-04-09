package com.example.feature_components.data.extension

import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.data.model.Component

/**
 * Extension function
 *
 * @author Zashaev Astemir on 2022-04-09
 */

/** Данный из бд в модель для слоя presentation */
fun ComponentsEntity.componentsEntityToComponents() = Component(
    contractId, componentName, accepted, countOfItem, dateOfAccept
)

/** Данный из presentation в модель для бд */
fun Component.componentToComponentEntity() =
    ComponentsEntity(contractId, componentName, personWhitchAccept, countOfItem, dateOfAccept)
