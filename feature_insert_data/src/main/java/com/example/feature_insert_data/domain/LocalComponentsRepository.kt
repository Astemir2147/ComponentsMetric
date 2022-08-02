/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain

import com.example.feature_insert_data.data.models.Component

/**
 * Интерфейс для работы с room и добавлением в него компонентов
 * @author Asanov Albek 25.06.2022
 */

interface LocalComponentsRepository {

    /**
     * Получение сегодняшней даты
     * @return сегодняшняя дата, конвертируемая в формате dd.MM.yyyy / MM.dd.yyyy
     */
    fun getActualDate() : String

    /**
     * Складывает категорию, брэнд и модель в одну строку
     * @param category - категория компонента (тип комплектующей)
     * @param brand - брэнд (производитель)
     * @param model - модель комплектующей
     * @return полное название комплектующей
     */
    fun getComponentName(category : String, brand : String, model : String) : String

    /**
     * Добавляет компонент в room
     * @param component - добавляемый компонент
     */
    fun addNewComponentToDb(component: Component) : Long
}