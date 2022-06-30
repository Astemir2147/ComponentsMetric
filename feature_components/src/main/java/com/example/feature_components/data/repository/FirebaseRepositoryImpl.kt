package com.example.feature_components.data.repository

import com.example.core.database.dao.ComponentsDao
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.domain.FirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Имплементация FirebaseRepository для получения данных из сети
 *
 * @author Asanov Albek 24.06.2022
 */

class FirebaseRepositoryImpl(
    private val componentsDao: ComponentsDao
) : FirebaseRepository{

    private val firebase = FirebaseFirestore.getInstance()
    private val componentsCollection = firebase.collection("Components")

    override suspend fun getComponentsFromFirebase() {

        componentsCollection.get()
                // Если успешно получен документ
            .addOnSuccessListener { snapshot ->

                // Парсинг компонента
                    val firebaseComponents = snapshot.documents.map { snapshotItem ->
                        ComponentsEntity(
                            contractId = snapshotItem.get("contractId") as Long,
                            componentName = snapshotItem.get("componentName").toString(),
                            accepted = snapshotItem.get("personWitchAccept").toString(),
                            countOfItem = snapshotItem.get("countOfItem").toString(),
                            dateOfAccept = snapshotItem.get("dateOfAccept").toString(),
                            statusOfComponent = snapshotItem.get("statusOfComponent").toString()
                        )
                }

                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            componentsDao.setAllComponents(firebaseComponents)
                        }
                    }
            }
                // Если получен не успешно ( пока ничего не делаем )
            .addOnFailureListener {  }
    }
}