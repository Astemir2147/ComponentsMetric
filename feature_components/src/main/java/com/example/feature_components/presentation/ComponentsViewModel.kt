package com.example.feature_components.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_components.domain.interactor.Interactor
import androidx.lifecycle.viewModelScope
import com.example.feature_components.data.model.Component
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    private var contractsListMutableLiveDataFromDb = MutableLiveData<List<Component>>()
    val contractListFromDb: LiveData<List<Component>>
        get() = contractsListMutableLiveDataFromDb
    val list: List<Component> = emptyList()

    fun init() {
        setContractsToDb()
        getContractsFromDb()
    }

    private fun setContractsToDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                componentsInteractor.setAllContractToDb(list)
            }
        }
    }

    private fun getContractsFromDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contracts = componentsInteractor.getComponentsFromDb()
                withContext(Dispatchers.Main) {
                    contractsListMutableLiveDataFromDb.postValue(contracts)
                }
            }
        }
    }
}