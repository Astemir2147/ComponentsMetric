/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain.interactor

import android.app.DatePickerDialog
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.InsertRepository

class InsertInteractorImpl(
    private val insertRepository: InsertRepository
) : InsertInteractor {
    override fun addComponentToDB(component: Component) {
        insertRepository.addToDatabase(component)
    }

    override fun getActualCalendarDate() = insertRepository.getActualDate()

    override fun newComponent(
        componentName: String,
        acceptedPersonName: String,
        itemsCount: String,
        acceptDate: String,
    ) = insertRepository.buildComponent(
                componentName, acceptedPersonName, itemsCount, acceptDate)

    override fun buildComponentName(category: String, brand: String, model: String) =
        insertRepository.getComponentName(category, brand, model)
}