package com.example.feature_components.domain.interactor

import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.Repository

class InteractorImpl(
    private val componentRepository: Repository
) : Interactor {
    override suspend fun getComponentsForStatus(status: String): List<Component>{
        return componentRepository.getComponentsForStatus(status)
    }

    override suspend fun getComponentsFromDb(status: String): List<Component> {
        return componentRepository.getAllComponentFromDb(status)
    }

    override suspend fun setAllContractToDb() {
        componentRepository.setAllComponentToDb()
    }

    override suspend fun searchComponent(query: String): List<Component>{
        return componentRepository.searchComponent(query)
    }


}