/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.data.repository

import com.example.core.database.dao.ComponentsDao
import com.example.feature_insert_data.data.extantion.componentToComponentEntity
import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.LocalComponentsRepository
import java.text.SimpleDateFormat
import java.util.*

class LocalComponentsRepositoryImpl(
    private val componentsDao : ComponentsDao,
    ) : LocalComponentsRepository {

    override fun addNewComponentToDb(component: Component) =
        componentsDao.insertNewComponent(component.componentToComponentEntity())

    /**
     * Получение сегодняшней даты
     */
    override fun getActualDate() : String {
        // Берём дату на данный момент
        val currentDate = Date()

        // Задаём ей формат
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // Возвращаем строку с сегодняшней датой в указанном формате
        return dateFormat.format(currentDate)
    }

    override fun getComponentName(
        category: String,
        brand: String,
        model: String) = "$category $brand $model"
    }