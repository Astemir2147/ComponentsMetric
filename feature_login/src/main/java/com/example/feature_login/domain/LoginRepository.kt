package com.example.feature_login.domain

import com.example.feature_login.data.Cookie
import com.example.feature_login.data.model.AuthDateUser

interface LoginRepository {
    suspend fun getCookieFromPreferences(): Cookie
    suspend fun saveCookieFromPreferences(cookie: Cookie)
}