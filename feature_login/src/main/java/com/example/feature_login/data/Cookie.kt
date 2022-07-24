package com.example.feature_login.data

import com.example.core.database.EMPTY

data class Cookie(
    val userName: String = String.EMPTY,
    val userMail: String,
    val userPassword: String
)