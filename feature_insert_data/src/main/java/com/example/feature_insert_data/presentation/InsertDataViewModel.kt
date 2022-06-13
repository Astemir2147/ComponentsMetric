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

    fun getDao() = insertInteractor.getRepository().getDao()

    fun getCountOfAllOrders() : Int {
        var size = 0

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                size = getDao().getAllComponents().size
            }
        }

        return size
    }

    fun insertNewComponent(component : Component) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertInteractor.insertNewComponent(component)
            }
        }
    }

    fun buildInsertComponent(
        componentName: String,
        acceptedPersonName: String,
        componentsCount: String,
        selectedDate: String
    )  = insertInteractor.newComponent(
            0,
                    componentName,
                    acceptedPersonName,
                    componentsCount,
                    selectedDate
                )


    fun getDateToday() = insertInteractor.getActualCalendarDate()

        fun insertComponent(component: Component) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                insertInteractor.addComponentToDB(component)
                }
            }
        }
    }