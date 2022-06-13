package com.example.feature_insert_data.domain

import com.example.feature_insert_data.data.models.Component
import com.google.firebase.firestore.DocumentReference

interface FirebaseRepository {
    //fun getComponentsFromFirebase()
    fun addComponentToFirebase(component : Component)

    // Это если нужно будет получить документ по его имени
    fun getFirebaseDoc(docName : String) : DocumentReference
}