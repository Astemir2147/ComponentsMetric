package com.example.componentsmetric.data.firestore

import com.example.componentsmetric.data.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Имплементация интерфейса [FirestoreDataStore],
 * служит для взаимодействия с FirebaseStore
 *@author Зашаев Астемир
 */
class FirestoreDataStoreImpl() : FirestoreDataStore {
 private val fireStore = Firebase.firestore
    /**
     * Получение всех комплектующих
     *
     * @return [Task] - результат асинхронного запроса получения всех комплектующих
     */
    override fun getDocumentFirestore(): Task<QuerySnapshot> {
        return fireStore.collection(Constants.USER_TABLE)
            // .document()
            // .collection(Constants.USER_TABLE)
            .get()
    }
}