package com.example.feature_components.data.repository

import androidx.lifecycle.LiveData
import com.example.core.database.dao.ComponentsDao
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.data.extension.componentToComponentEntity
import com.example.feature_components.data.extension.componentsEntityToComponents
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.Repository

class RepositoryImpl(private val dao: ComponentsDao) : Repository {

    override suspend fun searchComponent(query: String): List<Component> {
       return dao.searchComponent(query).map { it.componentsEntityToComponents() }
    }

    override suspend fun getComponentsForStatus(status: String): List<Component> {
        return dao.getComponentsForStatus(status).map { it.componentsEntityToComponents() }
    }

    override suspend fun getAllComponentFromDb(status: String): List<Component> {
        return dao.getComponentsForStatus(status).map { it.componentsEntityToComponents() }
    }

    //TODO(изменить параметр [components] на dto сети в дальнейшем))
    override suspend fun setAllComponentToDb(components: List<ComponentsEntity>) {
        dao.setAllComponents(components)
    }
}