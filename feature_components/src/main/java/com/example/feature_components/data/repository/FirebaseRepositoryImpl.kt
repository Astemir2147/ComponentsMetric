package com.example.feature_components.data.repository

import android.util.Log
import com.example.core.database.dao.ComponentsDao
import com.example.core.database.entity.ComponentsEntity
import com.example.feature_components.domain.FirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val componentsDao: ComponentsDao,
) : FirebaseRepository{

    private val firebase = FirebaseFirestore.getInstance()
    private val componentsCollection = firebase.collection("Components")

    override suspend fun getComponentsFromFirebase() {

        componentsCollection.get()
            .addOnSuccessListener { snapshot ->

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

                firebaseComponents.forEach { Log.i("Components", it.toString()) }
                snapshot.documents.forEach { Log.i("Snapshot", it.toString()) }

                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        componentsDao.setAllComponents(firebaseComponents)
                    }
                }

                Log.i("Components", "Данные загружены")
            }
            .addOnFailureListener { exeption ->
                Log.w("Components", "Данные из firestore не загружены.\n ${exeption.message}")
            }
    }
}