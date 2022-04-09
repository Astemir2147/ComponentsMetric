package com.example.feature_components.domain.interactor

import com.example.feature_components.data.model.Component

/**
 * Итерактор для работы с данными пользователя
 *
 * @author Zashaev Astemir on 2022-04-09
 */
interface Interactor {
    /** Получение всех контрактов из БД */
    suspend fun getComponentsFromDb(): List<Component>

    /** Внесение [components] в БД
     * TODO(изменить параметр [components] на dto сети в дальнейшем))
     * */
    suspend fun setAllContractToDb(components: List<Component>)
}