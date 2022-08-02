package com.example.feature_components.data.extension

import androidx.fragment.app.Fragment
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.R
import com.example.feature_components.data.model.Component
import com.google.android.material.snackbar.Snackbar

/**
 * Extension function
 *
 * @author Zashaev Astemir on 2022-04-09
 */

/** Данный из бд в модель для слоя presentation */
fun ComponentsEntity.componentsEntityToComponents() = Component(
    contractId, componentName, accepted, countOfItem, dateOfAccept, statusOfComponent
)

/** Данный из presentation в модель для бд */
fun Component.componentToComponentEntity() =
    ComponentsEntity(contractId, componentName, personWhichAccept, countOfItem, dateOfAccept, statusOfComponent)

fun Fragment.snackBar(message : String) =
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setTextColor(resources.getColor(R.color.light_green, null))
        .show()
