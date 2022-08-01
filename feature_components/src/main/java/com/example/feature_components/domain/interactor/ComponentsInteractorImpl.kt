package com.example.feature_components.domain.interactor

import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.data.extension.componentToComponentEntity
import com.example.feature_components.data.extension.componentsEntityToComponents
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.utils.servises.RemoteComponentsRepository
import com.example.feature_components.domain.utils.servises.LocalComponentsRepository
import com.example.feature_components.domain.utils.servises.firebase_callback.CallbackDataFromFirebase

class ComponentsInteractorImpl(
    private val componentRepository: LocalComponentsRepository,
    private val firebaseRepository: RemoteComponentsRepository
) : ComponentsInteractor {
    override suspend fun getComponentsForStatus(status: String): List<Component> =
        componentRepository.getComponentsForStatus(status).map { it.componentsEntityToComponents() }

    override suspend fun getComponentsFromDb(): List<Component> =
        componentRepository.getAllComponentFromDb().map { it.componentsEntityToComponents() }

    override suspend fun searchComponent(query: String): List<Component> =
        componentRepository.searchComponent(query).map { it.componentsEntityToComponents() }

    override suspend fun getComponentsFromFirebase(callback: CallbackDataFromFirebase) =
        firebaseRepository.getComponentsFromFirebase(callback)

    override suspend fun deleteRoomComponents(components: List<Component>) =
        componentRepository.deleteComponents(components.map { it.componentToComponentEntity() })

    override suspend fun setAllComponentsToDb(components: List<ComponentsEntity>) =
        componentRepository.setAllComponentToDb(components)
}