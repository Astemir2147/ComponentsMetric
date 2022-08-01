/**
 * Конвертер классов, преобразует модель в entity модель для room
 *
 * @author Asanov Albek
 */

package com.example.feature_insert_data.data.extantion

import androidx.fragment.app.Fragment
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_insert_data.R
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.presentation.InsertDataFragment
import com.google.android.material.snackbar.Snackbar

/** Данные из presentation в модель для бд */
fun Component.componentToComponentEntity() =
    ComponentsEntity(contractId, componentName, personWitchAccept, countOfItem, dateOfAccept, statusOfComponent)

fun Fragment.snackBar(message : String) =
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setTextColor(resources.getColor(R.color.light_green, null))
        .show()
