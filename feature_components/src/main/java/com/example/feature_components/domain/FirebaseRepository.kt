package com.example.feature_components.domain

import com.example.core.database.entity.ComponentsEntity

interface FirebaseRepository {
    /**
     * Получение данных из Firestore и запись в Room
     */
    suspend fun getComponentsFromFirebase()
}