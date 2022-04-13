package com.example.feature_login.data

interface AuthDataSource {
    suspend fun saveCookie(cookie: Cookie)
    suspend fun getCookie(): Cookie
}