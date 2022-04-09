package com.example.feature_components.domain

import com.example.feature_components.data.model.Component

/**
 * Интерфейс для реализации репозитория работы с базой данных
 *
 * @author Zashaev Astemir on 2022-04-09
 */
interface Repository {
    /** Возвращает контракты пиз бд */
    suspend fun getAllComponentFromDb(): List<Component>

    /** Занести данные в бд
     * TODO(изменить параметр [components] на dto сети в дальнейшем)
     * */
    suspend fun setAllComponentToDb(components: List<Component>)

}