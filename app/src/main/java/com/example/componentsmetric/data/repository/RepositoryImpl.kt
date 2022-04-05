package com.example.componentsmetric.data.repository

import com.example.componentsmetric.data.firestore.FirestoreDataStore
import com.example.componentsmetric.domain.dto.Product
import com.example.componentsmetric.domain.repository.Repository
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects

class RepositoryImpl() : Repository {

    override suspend fun getAllProduct(): List<Product> {
        val list : List<Product> = emptyList()
        // fireStore.getDocumentFirestore().addOnSuccessListener { result ->
        //     list = result.map { it.toObject<Product>() }
        // }
        return list

    }

    
}