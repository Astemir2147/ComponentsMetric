/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.domain

import com.example.feature_insert_data.data.models.Component

/**
 * Интерфейс для работы с room и добавлением в него компонентов
 * @author Asanov Albek 25.06.2022
 */

interface InsertRepository {

    /**
     * Получение сегодняшней даты
     * @return сегодняшняя дата, конвертируемая в формате dd.MM.yyyy / MM.dd.yyyy
     */
    fun getActualDate() : String

    /**
     * @param contractId - идентификатор контракта
     * @param componentName - название комплектующей
     * @param accepting - имя принимающего комплектующие
     * @param itemsCount - количество комплектующих
     * @param acceptDate - дата принятия
     * @return Новый компонент
     */
    fun buildComponent(
        contractId : Long,
        componentName : String,
        accepting : String,
        itemsCount : String,
        acceptDate : String
    ) : Component

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
    fun addNewComponentToDb(component: Component)
}