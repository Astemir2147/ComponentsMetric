package com.example.feature_login.data.model

import com.example.feature_login.data.Cookie

class AuthDateUser(
    val email: String = "",
    val password: String = "",
)

fun AuthDateUser.authDateUserToCookie() = Cookie(userMail = email, userPassword = password)