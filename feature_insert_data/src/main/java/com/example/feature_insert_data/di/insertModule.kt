/**
 * @author Asanov Albek
 */

package com.example.feature_insert_data.di

import com.example.core.database.base.MyDatabase
import com.example.core.database.dao.ComponentsDao
import com.example.feature_insert_data.data.repository.InsertRepositoryImpl
import com.example.feature_insert_data.domain.InsertRepository
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

    factory<InsertRepository> {
        InsertRepositoryImpl(
            componentsDao = get() as ComponentsDao
        ) }

    factory<InsertInteractor> { InsertInteractorImpl(
        insertRepository = get()
    ) }

    single { InsertValidator() }

    viewModel { InsertDataViewModel(
        insertInteractor = get(),
        validator = get()
    ) }
}

private val loadInsertModules by lazy { loadKoinModules(insertModule) }

fun insertInject() = loadInsertModules