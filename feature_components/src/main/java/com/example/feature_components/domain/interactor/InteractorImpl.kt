package com.example.feature_components.domain.interactor

import android.util.Log
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.FirebaseRepository
import com.example.feature_components.domain.Repository

class InteractorImpl(
    private val componentRepository: Repository,
    private val firebaseRepository: FirebaseRepository
) : Interactor {
    override suspend fun getComponentsForStatus(status: String): List<Component>{
        return componentRepository.getComponentsForStatus(status)
    }

    override suspend fun getComponentsFromDb(status: String): List<Component> {
        return componentRepository.getAllComponentFromDb(status)
    }

    /*override suspend fun setAllContractToDb() {
        componentRepository.setAllComponentToDb()
    }*/

    override suspend fun searchComponent(query: String): List<Component>{
        return componentRepository.searchComponent(query)
    }

    override suspend fun getComponentsFromFirebase() =
        firebaseRepository.getComponentsFromFirebase()

}