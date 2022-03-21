package com.example.componentsmetric.domain.interactor

import com.example.componentsmetric.data.firestore.FirestoreDataStore
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

class Interactor( private val storeDataStore: FirestoreDataStore,) {
    /**
     * Получение запроса на документ
     *
     * @return [Single] - асинхронный результат получения списка
     */
    fun getAllRunsFromCloud(): Task<QuerySnapshot> {
           return storeDataStore.getDocumentFirestore()
    }

}