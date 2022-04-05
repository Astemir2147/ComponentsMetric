package com.example.componentsmetric.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.componentsmetric.domain.useCase.GetAllProductUseCase
import com.example.componentsmetric.ui.dto.ComponentListItem
import com.example.componentsmetric.ui.mappers.productToComponentListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComponentsViewModel(
    private val getAllProductUseCase: GetAllProductUseCase
) : ViewModel() {

    private val _postsLoaded = MutableLiveData<List<ComponentListItem>>()
    val commentsLoaded: LiveData<List<ComponentListItem>> get() = _postsLoaded

    suspend fun init() {
        Log.d("my_tag", "ComponentsViewModel")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val allComponents = getAllProductUseCase.getAllProduct().map { it.productToComponentListItem() }
                withContext(Dispatchers.Main) {
                    _postsLoaded.value = allComponents
                }
            }
        }
    }
}
