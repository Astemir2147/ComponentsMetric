package com.example.feature_login.presentation.validation

/**
 * Класс содержащий методы салидаии для полей ввода при регистрации
 */
class Validator {

    /**
     * Метод проверки соответствия Email шаблону xxx@xx.xx
     *
     * @param email
     */
    fun validateEmail(email: String): Boolean = email.matches(emailRegex)

    /**
     * Метод проверки сложности пароля
     *
     * @param password
     */
    fun validatePassword(password: String): Boolean = password.contains(upperCase) &&
        password.contains(lowerCase) && password.contains(numbers)

    /**
     * Метод проверки пустоты имени пользователя
     *
     * @param username
     */
    fun validateUsername(username: String, password: String): Boolean =
        username.isEmpty() && password.isEmpty()

    companion object {
        private val emailRegex = "[A-Za-z0-9_.]+@[A-Za-z0-9_.]+".toRegex()
        private val upperCase = "[A-Z]+".toRegex()
        private val lowerCase = "[a-z]+".toRegex()
        private val numbers = "[0-9]+".toRegex()
    }
}