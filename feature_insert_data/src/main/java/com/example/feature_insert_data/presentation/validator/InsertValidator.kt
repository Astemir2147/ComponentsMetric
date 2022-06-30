/**
 * Класс для проверки на правильность данных
 *
 * @author Asanov Albek
 */

package com.example.feature_insert_data.presentation.validator

import java.text.SimpleDateFormat
import java.util.*

class InsertValidator {

    /**
     * Проверяет дату на правильность
     * @param date - дата, которую надо проверить
     * @return правильна ли дата, выбранная пользователем
     */
    fun isValidDate(date : Date) : Boolean {
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // Верхний порог даты, пока что установил 2030 год
        val maximalDate = simpleDateFormat.parse("01.01.2030")
        val today = simpleDateFormat.format(Date())
        val dateToday = simpleDateFormat.parse(today)

        return (date.time < maximalDate!!.time) && (date.time >= dateToday.time)
    }

}