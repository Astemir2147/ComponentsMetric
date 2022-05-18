/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.interactor.InsertInteractor
import com.example.feature_insert_data.presentation.validator.InsertValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class InsertDataViewModel(
    private val insertInteractor: InsertInteractor,
    private val validator : InsertValidator
) : ViewModel() {

    private var _insertedComponent = MutableLiveData<Component>()
    val insertedComponent : LiveData<Component> get() = _insertedComponent

    /**
     * Метод, меняющий дату, если она правильная
     */
    fun isCurrentDate(date : String) : Boolean{
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val dateToDate = dateFormat.parse(date)

        return validator.isValidDate(dateToDate)
    }

    fun getComponentName(category: String, brand: String, model: String) =
        insertInteractor.buildComponentName(category, brand, model)

    fun buildInsertComponent(
        componentName: String,
        acceptedPersonName: String,
        componentsCount: String,
        selectedDate: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val component = insertInteractor.newComponent(
                    componentName,
                    acceptedPersonName,
                    componentsCount,
                    selectedDate
                )
                withContext(Dispatchers.Main) {
                    _insertedComponent.value = component
                }
            }
        }
    }

    fun getDateToday() = insertInteractor.getActualCalendarDate()

        fun insertComponent(component: Component) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                insertInteractor.addComponentToDB(component)
                }
            }
        }
    }