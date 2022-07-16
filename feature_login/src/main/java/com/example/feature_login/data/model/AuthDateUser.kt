package com.example.feature_login.data.model

import com.example.feature_login.data.Cookie

class AuthDateUser(
    val email: String = "",
    val password: String = "",
) {
    var userName: String = ""
    constructor(
        userName: String = "",
        email: String = "",
        password: String = ""
    ) : this(email, password) {
        this.userName = userName
    }
}

fun AuthDateUser.authDateUserToCookie() = Cookie(userMail = email, userPassword = password)
fun AuthDateUser.authDateNewUserToCookie() = Cookie(userName = userName, userMail = email, userPassword = password)
