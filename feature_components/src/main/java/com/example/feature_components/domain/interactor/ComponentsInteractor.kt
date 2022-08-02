package com.example.feature_components.domain.interactor

import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.utils.servises.firebase_callback.CallbackDataFromFirebase

/**
 * Итерактор для работы с данными пользователя
 *
 * @author Zashaev Astemir on 2022-04-09
 */
interface ComponentsInteractor {

    /** Получение всех контрактов из БД с определенным статусом [status]
     * @param status может принимать значения ReadyForUse, Installed и Discarded*/
    suspend fun getComponentsForStatus(status: String): List<Component>

    /** Получение всех контрактов из БД */
    suspend fun getComponentsFromDb(): List<Component>

    /** Поиск компонента по ключевому слову [query]*/
    suspend fun searchComponent(query: String): List<Component>

    /** Добавление в room компонентов, полученных из firebase */
    suspend fun getComponentsFromFirebase(callback : CallbackDataFromFirebase)
    /** Удаляет компоненты из room
     * используется для повторной загрузки компонентов
     * из сети при наличии подключения к интернету*/
    suspend fun deleteRoomComponents(components : List<Component>)

    /**
     * Добавляет компоненты в room
     * @param components - добавляемые компоненты
     */
    suspend fun setAllComponentsToDb(components : List<ComponentsEntity>)
}