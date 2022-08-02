package com.example.feature_insert_data.data.repository

import com.example.feature_insert_data.data.models.Component
import com.example.feature_insert_data.domain.RemoteComponentsRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * @author Asanov Albek 06.11.2022
 *
 * Класс для работы с Firestore
 */

class RemoteComponentsRepositoryImpl : RemoteComponentsRepository {

    companion object {
        const val COMPONENTS_COLLECTION_PATH = "Components"
    }

    // Экземпляр базы
    val firebase = FirebaseFirestore.getInstance()

    // Коллекция компонентов
    val componentsCollection = firebase.collection(COMPONENTS_COLLECTION_PATH)

    override fun addComponentToFirebase(component: Component) {

        // Добавление документа
        componentsCollection.add(
            mapOf(
                "contractId" to component.contractId,
                "componentName" to component.componentName,
                "personWitchAccept" to component.personWitchAccept,
                "countOfItem" to component.countOfItem,
                "dateOfAccept" to component.dateOfAccept,
                "statusOfComponent" to component.statusOfComponent
            )
        )
    }

    override fun getFirebaseDoc(docName: String) : DocumentReference =
        componentsCollection.document(docName)
}