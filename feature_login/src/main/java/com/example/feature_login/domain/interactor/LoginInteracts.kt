package com.example.feature_login.domain.interactor

import com.example.feature_login.data.Cookie
import com.example.feature_login.data.model.AuthDateUser

interface LoginInteracts {
    suspend fun checkIfAuthentificated(): Boolean
    suspend fun saveCookieFromPreferences(cookie: Cookie)
}