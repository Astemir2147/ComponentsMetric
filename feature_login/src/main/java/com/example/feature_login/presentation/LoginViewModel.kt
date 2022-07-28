package com.example.feature_login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_login.data.Cookie
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.domain.interactor.LoginInteracts
import com.example.feature_login.presentation.validation.Validator
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginInteracts: LoginInteracts,
    private val validator: Validator
) : ViewModel() {

    private val mutableLoginAttemptResultLiveData = MutableLiveData<Boolean>()
    private val mutablePasswordRestoreResultLiveData = MutableLiveData<Boolean>()
    // private val mutableValidationErrorLiveData = MutableLiveData<ValidationError>()
    // private val mutableNetworkErrorLiveData = MutableLiveData<HttpResult>()

    /*
     * Указывает на результат выполнения авторизации
     */
    val loginAttemptResultLiveData: LiveData<Boolean>
        get() = mutableLoginAttemptResultLiveData

    /*
    * Указывает на результат выполнения запроса на восстановление пароля
    */
    val passwordRestoreResultLiveData: LiveData<Boolean>
        get() = mutablePasswordRestoreResultLiveData

    // private fun onSuccess() {
    //     mutableLoginAttemptResultLiveData.value = true
    // }

    fun saveCookieFromPreferences(cookie: Cookie) {
        viewModelScope.launch {
            loginInteracts.saveCookieFromPreferences(cookie = cookie)
        }
    }

    /**
     * Выполняет проверку авторизован ли пользователь
     */
    fun checkLogined() {
        viewModelScope.launch {
            if (loginInteracts.checkIfAuthentificated()) {
                mutableLoginAttemptResultLiveData.value = true
            }
        }
    }

    fun validateFields(user: AuthDateUser): Boolean {
        return validator.validateFields(user)
    }
}
