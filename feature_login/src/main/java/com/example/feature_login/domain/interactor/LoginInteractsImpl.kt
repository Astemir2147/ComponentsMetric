package com.example.feature_login.domain.interactor

import com.example.feature_login.data.Cookie
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.domain.LoginRepository

class LoginInteractsImpl(private val loginRepository: LoginRepository) : LoginInteracts {

    override suspend fun checkIfAuthentificated(): Boolean =
        loginRepository.getCookieFromPreferences().userMail.isNotEmpty()

    override suspend fun saveCookieFromPreferences(cookie: Cookie) {
        loginRepository.saveCookieFromPreferences(cookie = cookie)
    }
}