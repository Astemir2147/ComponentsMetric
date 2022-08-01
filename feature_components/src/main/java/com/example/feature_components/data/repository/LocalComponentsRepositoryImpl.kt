package com.example.feature_components.data.repository

import com.example.core.database.dao.ComponentsDao
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.domain.utils.servises.LocalComponentsRepository

class LocalComponentsRepositoryImpl(private val dao: ComponentsDao) : LocalComponentsRepository {

    override suspend fun searchComponent(query: String): List<ComponentsEntity> {
        return dao.searchComponent(query)
    }

    override suspend fun getComponentsForStatus(status: String): List<ComponentsEntity> {
        return dao.getComponentsForStatus(status)
    }

    override suspend fun getAllComponentFromDb(): List<ComponentsEntity> {
        return dao.getAllComponents()
    }

    //TODO(изменить параметр [components] на dto сети в дальнейшем))
    override suspend fun setAllComponentToDb(components: List<ComponentsEntity>) {
            dao.setAllComponents(components)
    }

    override suspend fun deleteComponents(components: List<ComponentsEntity>) {
        dao.deleteAllComponents(components)
    }
}