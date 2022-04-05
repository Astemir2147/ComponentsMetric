package com.example.componentsmetric.data

import com.example.componentsmetric.data.firestore.FirestoreDataStoreImpl
import com.example.componentsmetric.data.repository.RepositoryImpl
import com.example.componentsmetric.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    single { FirestoreDataStoreImpl() }
    factory <Repository>{RepositoryImpl()}
}