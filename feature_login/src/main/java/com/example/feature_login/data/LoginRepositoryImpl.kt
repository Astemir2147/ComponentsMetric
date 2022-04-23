package com.example.feature_login.data

import com.example.feature_login.data.base.LoginFirebaseAuth
import com.example.feature_login.data.model.AuthDateUser
import com.example.feature_login.domain.LoginRepository

class LoginRepositoryImpl(
    private val authDataSource: AuthDataSource
) : LoginRepository {

    override suspend fun getCookieFromPreferences(): Cookie = authDataSource.getCookie()

    override suspend fun saveCookieFromPreferences(cookie: Cookie) {
        authDataSource.saveCookie(cookie)
    }
}