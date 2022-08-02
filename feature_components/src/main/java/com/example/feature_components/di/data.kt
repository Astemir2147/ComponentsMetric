package com.example.feature_components.di

import com.example.core.database.dao.ComponentsDao
import com.example.core.database.base.MyDatabase
import com.example.feature_components.data.repository.RemoteComponentsRepositoryImpl
import com.example.feature_components.data.repository.LocalComponentsRepositoryImpl
import com.example.feature_components.domain.utils.servises.RemoteComponentsRepository
import com.example.feature_components.domain.utils.servises.LocalComponentsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { MyDatabase.buildDatabase(androidApplication()) }
    single { get<MyDatabase>().getDao() }

    factory<LocalComponentsRepository> { LocalComponentsRepositoryImpl(get() as ComponentsDao) }

    factory<RemoteComponentsRepository> { RemoteComponentsRepositoryImpl() }

}