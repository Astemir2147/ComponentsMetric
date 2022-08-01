package com.example.feature_components.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.core.database.entity.ComponentsEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.example.feature_components.data.model.Component
import com.example.feature_components.domain.interactor.ComponentsInteractor
import com.example.feature_components.domain.utils.networkUtil.NetworkDelegate
import com.example.feature_components.domain.utils.servises.firebase_callback.CallbackDataFromFirebase

/**
 * Вьюмодель для получения списка контрактов
 *
 * @property componentsInteractor интерактор
 *
 * @author Zashaev Astemir on 2022-04-09
 */
class ComponentsViewModel(
    private val componentsInteractor: ComponentsInteractor,
    private val networkDelegate: NetworkDelegate
) : ViewModel(), NetworkDelegate by networkDelegate {

    companion object {
        private const val READY_FOR_USE = "ReadyForUse"
        private const val INSTALLED = "Installed"
        private const val DISCARDED = "Discarded"
    }

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

    /**
     * Удаление всех контрактов для загрузки новых контрактов
     */
    private fun deleteAllContractsToDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val allContracts = componentsInteractor.getComponentsFromDb()
                componentsInteractor.deleteRoomComponents(allContracts)
            }
        }
    }

    fun init() {
        deleteAllContractsToDb()
        loadComponentsToRoom()
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

    fun getAcceptedContractsFromDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contracts = componentsInteractor
                    .getComponentsForStatus(READY_FOR_USE)
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
                    .getComponentsForStatus(INSTALLED)
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
                    .getComponentsForStatus(DISCARDED)
                withContext(Dispatchers.Main) {
                    discardedContractsListMutableLiveDataFromDb.postValue(contracts)
                }
            }
        }
    }

    /**
     * Функция загрузки данных из сети в room
     */
    private fun loadComponentsToRoom() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                componentsInteractor.getComponentsFromFirebase( object : CallbackDataFromFirebase {
                        override fun onDataReceived(componentsFromFirebase: List<ComponentsEntity>) {
                            setAllComponentsToDb(componentsFromFirebase)
                        }
                    })
            }
        }
    }

    private fun setAllComponentsToDb(components : List<ComponentsEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                componentsInteractor.setAllComponentsToDb(components)
            }
        }
    }
}