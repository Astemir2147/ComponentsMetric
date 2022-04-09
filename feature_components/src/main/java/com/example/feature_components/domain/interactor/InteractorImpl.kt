package com.example.feature_components.domain.interactor

import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.Repository

class InteractorImpl(
    private val componentRepository: Repository
) : Interactor {
    override suspend fun getComponentsFromDb(): List<Component> {
        return componentRepository.getAllComponentFromDb()
    }

    override suspend fun setAllContractToDb(components: List<Component>) {
        componentRepository.setAllComponentToDb(components)
    }
}