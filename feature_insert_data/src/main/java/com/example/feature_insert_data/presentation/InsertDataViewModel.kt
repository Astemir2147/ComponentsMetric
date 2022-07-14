/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.interactor.InsertInteractor
import com.example.feature_insert_data.presentation.validator.InsertValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * ViewModel для добавления компонентов
 *
 * @author Asanov Albek 25.06.2022
 */

class InsertDataViewModel(
    private val insertInteractor: InsertInteractor,
    private val validator : InsertValidator
) : ViewModel() {
    /**
     * Метод, меняющий дату, если она правильная
     */

    fun addComponentToFirebase(component: Component) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertInteractor.addComponentToFirebase(component)
            }
        }
    }

    fun isCurrentDate(date : String) : Boolean {
        val dateFormat = SimpleDateFormat("MM.dd.yyyy", Locale.getDefault())

        val dateToDate = dateFormat.parse(date)

        return validator.isValidDate(dateToDate)
    }

    fun getComponentName(category: String, brand: String, model: String) =
        insertInteractor.buildComponentName(category, brand, model)

    fun insertNewComponent(component : Component) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertInteractor.insertNewComponent(component)
            }
        }
    }

    fun getDateToday() = insertInteractor.getActualCalendarDate()
    }