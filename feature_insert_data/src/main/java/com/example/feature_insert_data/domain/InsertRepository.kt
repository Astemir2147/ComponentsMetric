/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain

import android.app.DatePickerDialog
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_insert_data.data.models.Component

interface InsertRepository {

    /**
     * Добавление в базу
     */
    fun addToDatabase(component : Component)

    /**
     * Получение сегодняшней даты
     */
    fun getActualDate() : String

    fun buildComponent(
        componentName : String,
        senderName : String,
        itemsCount : String,
        acceptDate : String
    ) : Component

    fun getComponentName(category : String, brand : String, model : String) : String
}