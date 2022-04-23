package com.example.feature_components.domain.interactor

import com.example.feature_components.data.model.Component

/**
 * Итерактор для работы с данными пользователя
 *
 * @author Zashaev Astemir on 2022-04-09
 */
interface Interactor {

    /** Получение всех контрактов из БД */
    suspend fun getComponentsForStatus(status: String): List<Component>

    /** Получение всех контрактов из БД с определенным статусом [status] */
    suspend fun getComponentsFromDb(status: String): List<Component>

    /** Внесение данных в БД */
    suspend fun setAllContractToDb()

    /** Поиск компонента по ключевому слову [query]*/
    suspend fun searchComponent(query: String): List<Component>
}