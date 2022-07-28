package com.example.feature_login.data

import android.content.SharedPreferences

class AuthDataSourceImpl(
    private val sharedPreference: SharedPreferences,
    private val sharedPreferenceEditor: SharedPreferences.Editor
) : AuthDataSource {
    override suspend fun saveCookie(cookie: Cookie) {

        sharedPreference.edit()
            .putString(USER_MAIL_PREF_KEY, cookie.userMail)
            .putString(USER_PASSWORD_PREF_KEY, cookie.userPassword)
            .apply()
    }

    override suspend fun getCookie(): Cookie {
        val mail = sharedPreference.getString(USER_MAIL_PREF_KEY, String()).orEmpty()
        val password = sharedPreference.getString(USER_PASSWORD_PREF_KEY, String()).orEmpty()
        val name = sharedPreference.getString(USER_NAME_PREF_KEY, null).orEmpty()
        return Cookie(mail, password, name)
    }

    companion object {
        private const val USER_NAME_PREF_KEY = "userName"
        private const val USER_MAIL_PREF_KEY = "userMail"
        private const val USER_PASSWORD_PREF_KEY = "userPassword"
    }
}