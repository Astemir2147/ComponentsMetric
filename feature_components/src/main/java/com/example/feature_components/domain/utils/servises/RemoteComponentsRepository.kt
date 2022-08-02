package com.example.feature_components.domain.utils.servises

import com.example.feature_components.domain.utils.servises.firebase_callback.CallbackDataFromFirebase

/**
 * Интерфейс для работы с Firebase Firestore, для получения компонентов из сети
 *
 * @author Asanov Albek 25.06.2022
 */

interface RemoteComponentsRepository {
    /**
     * Получение данных из Firestore и запись в Room
     */
    suspend fun getComponentsFromFirebase(firebaseCallback : CallbackDataFromFirebase)
}