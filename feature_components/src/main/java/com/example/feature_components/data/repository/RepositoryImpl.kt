package com.example.feature_components.data.repository

import com.example.core.database.dao.ComponentsDao
import com.example.feature_components.data.extension.componentToComponentEntity
import com.example.feature_components.data.extension.componentsEntityToComponents
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.Repository

class RepositoryImpl(private val dao: ComponentsDao) : Repository {

    override suspend fun getAllComponentFromDb(): List<Component> {
        return dao.getAllComponents().map { it.componentsEntityToComponents() }
    }

    override suspend fun setAllComponentToDb(components: List<Component>) {
        dao.setAllComponents(listOf(setFakeDate().componentToComponentEntity()))
    }

    private fun setFakeDate(): Component {
        return Component(
            1, "Processor: i5-3570K", "Ivan Ivanov",
            "2", "12.02.2022"
        )
    }
}