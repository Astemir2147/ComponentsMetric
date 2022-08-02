package com.example.feature_components.data.repository

import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.domain.utils.servises.RemoteComponentsRepository
import com.example.feature_components.domain.utils.servises.firebase_callback.CallbackDataFromFirebase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Имплементация FirebaseRepository для получения данных из сети
 *
 * @author Asanov Albek 24.06.2022
 */

class RemoteComponentsRepositoryImpl : RemoteComponentsRepository {

    companion object {
        private const val CONTRACT_ID = "contractId"
        private const val COMPONENT_NAME = "componentName"
        private const val PERSON_WITCH_ACCEPT = "personWitchAccept"
        private const val COUNT_OF_ITEM = "countOfItem"
        private const val DATE_OF_ACCEPT = "dateOfAccept"
        private const val STATUS_OF_COMPONENT = "statusOfComponent"
        private const val COLLECTION_OF_COMPONENTS = "Components"
    }

    private val firebase = FirebaseFirestore.getInstance()
    private val componentsCollection = firebase.collection(COLLECTION_OF_COMPONENTS)

    override suspend fun getComponentsFromFirebase(firebaseCallback : CallbackDataFromFirebase) {
        var firebaseComponents = listOf<ComponentsEntity>()
        componentsCollection.get()
                // Если успешно получен документ
            .addOnSuccessListener { snapshot ->
                firebaseComponents = snapshot.documents.map { snapshotItem ->
                    with(snapshotItem) {
                        ComponentsEntity(
                            contractId = getLong(CONTRACT_ID)!!,
                            componentName = getString(COMPONENT_NAME)!!,
                            accepted = getString(PERSON_WITCH_ACCEPT)!!,
                            countOfItem = getString(COUNT_OF_ITEM)!!,
                            dateOfAccept = getString(DATE_OF_ACCEPT)!!,
                            statusOfComponent = getString(STATUS_OF_COMPONENT)!!
                        )
                    }
                }
                firebaseCallback.onDataReceived(firebaseComponents)
            }
    }
}