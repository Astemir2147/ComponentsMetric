/**
 * Конвертер классов, преобразует модель в entity модель для room
 *
 * @author Asanov Albek
 */

package com.example.feature_insert_data.data.extantion

import com.example.core.database.entity.ComponentsEntity
import com.example.feature_insert_data.data.models.Component
/** Данный из presentation в модель для бд */
fun Component.componentToComponentEntity() =
    ComponentsEntity(contractId, componentName, personWitchAccept, countOfItem, dateOfAccept, statusOfComponent)
