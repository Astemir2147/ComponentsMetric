package com.example.feature_components.domain

/**
 * Интерфейс для работы с Firebase Firestore, для получения компонентов из сети
 *
 * @author Asanov Albek 25.06.2022
 */

interface FirebaseRepository {
    /**
     * Получение данных из Firestore и запись в Room
     */
    suspend fun getComponentsFromFirebase()
}