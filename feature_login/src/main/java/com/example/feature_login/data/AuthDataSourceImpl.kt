package com.example.feature_login.data

import android.content.SharedPreferences

class AuthDataSourceImpl(
    private val sharedPreference: SharedPreferences,
    private val sharedPreferenceEditor: SharedPreferences.Editor
) : AuthDataSource {
    override suspend fun saveCookie(cookie: Cookie) {

        sharedPreference.edit()
            .putString(userMailKey, cookie.userMail)
            .putString(userPasswordKey, cookie.userPassword)
            .apply()
    }

    override suspend fun getCookie(): Cookie {
        val mail = sharedPreference.getString(userMailKey, String()).orEmpty()
        val password = sharedPreference.getString(userPasswordKey, String()).orEmpty()
        return Cookie(mail, password)
    }

    companion object {
        private const val userMailKey = "userMail"
        private const val userPasswordKey = "userPassword"
    }
}