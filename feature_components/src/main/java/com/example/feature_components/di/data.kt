package com.example.feature_components.di

import com.example.core.database.dao.ComponentsDao
import com.example.feature_components.data.MyDatabase
import com.example.feature_components.data.repository.RepositoryImpl
import com.example.feature_components.domain.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { MyDatabase.buildDatabase(androidApplication()) }
    single { get<MyDatabase>().getDao() }

    factory<Repository> { RepositoryImpl(get() as ComponentsDao) }

}