package com.example.feature_components.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.feature_components.data.DatabaseConstStatus
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.interactor.Interactor
import kotlin.contracts.contract

/**
 * Вьюмодель для получения списка контрактов
 *
 * @property componentsInteractor интерактор
 *
 * @author Zashaev Astemir on 2022-04-09
 */
class ComponentsViewModel(
    private val componentsInteractor: Interactor
) : ViewModel() {

    private var acceptedContractsListMutableLiveDataFromDb = MutableLiveData<List<Component>>()
    val acceptedContractListFromDb: LiveData<List<Component>>
        get() = acceptedContractsListMutableLiveDataFromDb

    private var installedContractsListMutableLiveDataFromDb = MutableLiveData<List<Component>>()
    val installedListFromDb: LiveData<List<Component>>
        get() = installedContractsListMutableLiveDataFromDb

    private var discardedContractsListMutableLiveDataFromDb = MutableLiveData<List<Component>>()
    val discardedListFromDb: LiveData<List<Component>>
        get() = discardedContractsListMutableLiveDataFromDb

    private val searchedContractList = MutableLiveData<List<Component>>()
    val searchContrac: LiveData<List<Component>>
        get() = searchedContractList

    fun init() {
        setContractsToDb()
    }

    fun searchComponent(query: String):LiveData<List<Component>> {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contracts = componentsInteractor.searchComponent(query)

                withContext(Dispatchers.Main) {
                    searchedContractList.value = contracts
                }
            }
        }
        return searchContrac
    }

    fun setContractsToDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                componentsInteractor.setAllContractToDb()
            }
        }
    }

    fun getAcceptedContractsFromDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contracts = componentsInteractor
                    .getComponentsFromDb(DatabaseConstStatus.READY_FOR_USE)
                withContext(Dispatchers.Main) {
                    acceptedContractsListMutableLiveDataFromDb.postValue(contracts)
                }
            }
        }
    }

    fun getInstalledComponentsFromDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contracts = componentsInteractor
                    .getComponentsForStatus(DatabaseConstStatus.INSTALLED)
                withContext(Dispatchers.Main) {
                    installedContractsListMutableLiveDataFromDb.postValue(contracts)
                }
            }
        }
    }

    fun getDiscardedComponentsFromDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contracts = componentsInteractor
                    .getComponentsForStatus(DatabaseConstStatus.DISCARDED)
                withContext(Dispatchers.Main) {
                    discardedContractsListMutableLiveDataFromDb.postValue(contracts)
                }
            }
        }
    }
}