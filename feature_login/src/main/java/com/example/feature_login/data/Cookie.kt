package com.example.feature_login.data

data class Cookie(
    val userMail: String,
    val userPassword: String
) {
    constructor(
        userName: String,
        userMail: String,
        userPassword: String
    ) : this(userMail, userPassword)
}