package com.example.feature_components.domain

import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.data.model.Component

/**
 * Интерфейс для реализации репозитория работы с базой данных
 *
 * @author Zashaev Astemir on 2022-04-09
 */
interface Repository {
    /** Возвращает контракты из бд по статусу [status] */
    suspend fun getComponentsForStatus(status: String): List<Component>

    /** Возвращает контракты из бд */
    suspend fun getAllComponentFromDb(status: String): List<Component>

    /** Занести данные в бд
     * upd : Asanov Albek 13.06.2022
     * */
    suspend fun setAllComponentToDb(components : List<ComponentsEntity>)

    /** Поиск компонента по ключевому слову [query]*/
    suspend fun searchComponent(query: String): List<Component>
}