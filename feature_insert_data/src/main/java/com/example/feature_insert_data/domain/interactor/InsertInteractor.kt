/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain.interactor

import android.app.DatePickerDialog
import com.example.feature_insert_data.data.models.Component

interface InsertInteractor {
    fun addComponentToDB(component : Component)
    fun getActualCalendarDate() : String
    fun newComponent(
        componentName: String,
        senderName: String, itemsCount: String,
        acceptDate: String) : Component
    fun buildComponentName(category: String, brand: String, model : String) : String
}