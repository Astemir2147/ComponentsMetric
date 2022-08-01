package com.example.feature_insert_data.domain

import com.example.feature_insert_data.data.models.Component
import com.google.firebase.firestore.DocumentReference

/**
 * Интерфейс для работы с сервером Firebase Firestore, для добавления в него компонента
 *
 * @author Asanov Albek 25.06.2022
 */

interface RemoteComponentsRepository {
    /**
     * Добавляет компонент на сервер Firebase Firestore
     * @param component - добавляемый компонент
     */
    fun addComponentToFirebase(component : Component)

    /**
     * Получает документ из Firestore по названию
     * @param docName - название документа
     * @return документ в виде DocumentReference
     */
    fun getFirebaseDoc(docName : String) : DocumentReference
}