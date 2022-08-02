/**
 * Модуль для добавления компонентов
 *
 * @author Asanov Albek 25.06.2022
 */

package com.example.feature_insert_data.di

import com.example.core.database.base.MyDatabase
import com.example.core.database.dao.ComponentsDao
import com.example.feature_insert_data.data.repository.RemoteComponentsRepositoryImpl
import com.example.feature_insert_data.data.repository.LocalComponentsRepositoryImpl
import com.example.feature_insert_data.domain.RemoteComponentsRepository
import com.example.feature_insert_data.domain.LocalComponentsRepository
import com.example.feature_insert_data.domain.interactor.InsertInteractor
import com.example.feature_insert_data.domain.interactor.InsertInteractorImpl
import com.example.feature_insert_data.presentation.InsertDataViewModel
import com.example.feature_insert_data.presentation.validator.InsertValidator
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val insertModule = module {
    single { MyDatabase.buildDatabase(androidApplication()) }
    single { get<MyDatabase>().getDao() }

    factory<LocalComponentsRepository> {
        LocalComponentsRepositoryImpl(
            componentsDao = get() as ComponentsDao
        ) }

    factory<RemoteComponentsRepository> {
        RemoteComponentsRepositoryImpl()
    }

    factory<InsertInteractor> { InsertInteractorImpl(
        insertRepository = get(),
        firebaseRepository = get()
    ) }

    single { InsertValidator() }

    viewModel { InsertDataViewModel(
        insertInteractor = get(),
        validator = get()
    ) }
}

private val loadInsertModules by lazy { loadKoinModules(insertModule) }

fun insertInject() = loadInsertModules