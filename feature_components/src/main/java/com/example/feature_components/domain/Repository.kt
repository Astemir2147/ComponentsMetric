package com.example.feature_components.domain

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

    /** Занести данные в бд */
    suspend fun setAllComponentToDb()
}