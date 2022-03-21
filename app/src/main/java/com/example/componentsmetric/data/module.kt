package com.example.componentsmetric.data

import com.example.componentsmetric.data.firestore.FirestoreDataStore
import com.example.componentsmetric.data.firestore.FirestoreDataStoreImpl
import com.example.componentsmetric.data.repository.RepositoryImpl
import com.example.componentsmetric.domain.repository.Repository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataModule = module {
    // single { FirebaseFirestore.getInstance() }
    single { FirestoreDataStoreImpl() }
// factory <FirestoreDataStore>{ FirestoreDataStoreImpl() }
    factory <Repository>{RepositoryImpl(get())}

    // single { ConstantsProvider() }
    // single { HttpClientProvider(get()) }
    // factory<PostApi> { PostsApiImpl(get()) }

    // factory<PostsRepository> { PostsRepositoryImpl(get())}
    //
    // factory<CommentApi> { CommentApiImpl(get()) }
    // factory<CommentRepository> { CommentRepositoryImpl(get()) }
//    factory<PostResponseMapper> { PostResponseMapperImpl() }
//    factory<PostsRepository> { PostsRepositoryImpl(get(),get()) }
}