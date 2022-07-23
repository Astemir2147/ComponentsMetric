package com.example.feature_login.data.model

import com.example.feature_login.data.Cookie

class AuthDateUser(
    val email: String = String.EMPTY,
    val password: String = String.EMPTY,
    val userName: String = String.EMPTY
)

val String.Companion.EMPTY
    get() = ""

fun AuthDateUser.authDateUserToCookie() = Cookie(userName = userName, userMail = email, userPassword = password)
