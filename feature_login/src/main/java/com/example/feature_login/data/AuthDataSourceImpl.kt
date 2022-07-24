package com.example.feature_login.data

import android.content.SharedPreferences

class AuthDataSourceImpl(
    private val sharedPreference: SharedPreferences,
    private val sharedPreferenceEditor: SharedPreferences.Editor
) : AuthDataSource {
    override suspend fun saveCookie(cookie: Cookie) {

        sharedPreference.edit()
            .putString(USER_MAIL_KEY, cookie.userMail)
            .putString(USER_PASSWORD_KEY, cookie.userPassword)
            .apply()
    }

    override suspend fun getCookie(): Cookie {
        val mail = sharedPreference.getString(USER_MAIL_KEY, String()).orEmpty()
        val password = sharedPreference.getString(USER_PASSWORD_KEY, String()).orEmpty()
        val name = sharedPreference.getString(USER_NAME, null).orEmpty()
        return Cookie(mail, password, name)
    }

    companion object {
        private const val USER_NAME = "userName"
        private const val USER_MAIL_KEY = "userMail"
        private const val USER_PASSWORD_KEY = "userPassword"
    }
}