package com.example.feature_login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_login.data.Cookie
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.domain.interactor.LoginInteracts
import com.example.feature_login.presentation.validation.Validator
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val loginInteracts: LoginInteracts,
    private val validator: Validator
) : ViewModel(){

    fun saveCookieFromPreferences(cookie: Cookie) {
        viewModelScope.launch {
            loginInteracts.saveCookieFromPreferences(cookie = cookie)
        }
    }

    fun validateFields(user: AuthDateUser): Boolean {
        return validator.validateFields(user)
    }
}