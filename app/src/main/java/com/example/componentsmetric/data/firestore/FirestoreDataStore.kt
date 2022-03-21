package com.example.componentsmetric.data.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface FirestoreDataStore {

    /**
     * Получение документа пользователя
     *
     * @return [Task] - результат асинхронного запроса получения документа
     */
    fun getDocumentFirestore(): Task<QuerySnapshot>

}