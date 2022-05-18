/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.data.repository

import com.example.core.database.dao.ComponentsDao
import com.example.feature_insert_data.data.extantion.componentToComponentEntity
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.InsertRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.text.StringBuilder

class InsertRepositoryImpl(
    private val componentsDao : ComponentsDao,
    ) : InsertRepository {

    override fun addToDatabase(component : Component) {
        val components = componentsDao.getAllComponents().toMutableList()
        components.add(component.componentToComponentEntity())
        componentsDao.setAllComponents(components.toList())
    }

    /**
     * Получение сегодняшней даты
     * Пока что нигде не используется
     */
    override fun getActualDate() : String {
        // Берём дату на данный момент
        val currentDate = Date()

        // Задаём ей формат
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // Возвращаем строку с сегодняшней датой в указанном формате
        return dateFormat.format(currentDate)
    }

    override fun buildComponent(
        componentName: String,
        acceptedPersonName: String, itemsCount: String,
        acceptDate: String) = Component(
            contractId = 4,
            componentName = componentName,
            personWitchAccept = acceptedPersonName,
            countOfItem = itemsCount,
            dateOfAccept = acceptDate,
            statusOfComponent = "Installed"
        )

    override fun getComponentName(
        category: String,
        brand: String,
        model: String) = StringBuilder().apply {
            append("$category ")
            append("$brand ")
            append("$model ")
        }.toString()
    }